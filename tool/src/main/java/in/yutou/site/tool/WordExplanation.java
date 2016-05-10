package in.yutou.site.tool;

import java.sql.DriverManager;

/**
 * Created by xiuxiu on 9/05/2016.
 */
public class WordExplanation {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        jdbcTemplate.queryForObject("select count(*) from t_actor", Integer.class);
    }

}
