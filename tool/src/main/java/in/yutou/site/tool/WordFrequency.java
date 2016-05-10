package in.yutou.site.tool;

import in.yutou.idgenerator.IdGenerator;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class WordFrequency {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("20000.txt"))));

    Map<String, Map<String, Integer>> map = generateMap(br);

    String sql = generateSQL(map);

    saveSQL(sql);
  }

  private static void saveSQL(String sql) throws IOException {
    FileWriter w = new FileWriter(new File("dict_word_frequencies.sql"));
    w.write(sql);
    w.close();
  }

  private static String generateSQL(Map<String, Map<String, Integer>> map) throws IOException {
    String sql = "";
    for (String word : map.keySet()) {
      long id = IdGenerator.generateId();
      sql += "INSERT INTO dict_words VALUES (" + id + ", '" + word + "', null, null, null, null, now());\n";
      System.out.println(word);
      for (String part : map.get(word).keySet()) {
        sql += "INSERT INTO dict_word_frequencies VALUES (" + IdGenerator.generateId() + ", " + id + ", '" + part + "', " + map.get(word).get(part) + ");\n";
        System.out.print(" " + part + " -> ");
        System.out.println(map.get(word).get(part));
      }
    }

    return sql;
  }

  private static Map<String, Map<String, Integer>> generateMap(BufferedReader br) throws IOException {
    Map<String, Map<String, Integer>> map = new HashMap<String, Map<String, Integer>>();

    String line;
    while ((line = br.readLine()) != null) {
      if (line != null && line.matches("\\d+\\s[[a-z][A-Z]']+\\s\\w+")) {
        String[] splited = line.split(" ");
        if (splited != null && splited[0] != null) {
          int rank = Integer.parseInt(splited[0]);
          String word = splited[1].toLowerCase();
          String part = splited[2];

          Map<String, Integer> wordRank = map.get(word) == null ? new HashMap<String, Integer>() : map.get(word);
          wordRank.put(part, rank);
          map.put(word, wordRank);
        }
      }
    }
    return map;
  }

}
