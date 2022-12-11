/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yueyi
 * @version : SqlBuilder.java, v 0.1 2022年12月10日 12:31 yueyi Exp $
 */
public class SqlBuilder {

    @Test
    public void test() {
        String sql = "==>  Preparing: SELECT ind_t.customer_id AS `customer_id` , ind_t.customer_name AS `customer_name` , `opt_num` as "
                + "`indicator_value` , ind_t.biz_date AS `biz_date` FROM adm_ap_mct_mb_yuedian_ka_seat_permission_1d AS per_t INNER JOIN "
                + "adm_ap_mct_mb_yuandian_ka_customer_yes_1d AS cus_t ON per_t.perm_seat_id = cus_t.owner_seat_id AND cus_t.dt = ? INNER "
                + "JOIN adm_ap_mct_mb_yuandian_industry_analysis_customer_base AS ind_t ON cus_t.customer_id = ind_t.customer_id AND "
                + "ind_t.biz_date IN ( ? ) AND biz_data_type = ? AND first_industry_code = ? WHERE per_t.dt = ? and per_t.id = ? order by"
                + " opt_num DESC limit 500 \n"
                + "==> Parameters: 20221202(String), 20221202(String), one_d(String), G007(String), 20221202(String), 10026069(String)";

        String[] sqls = sql.split("\\n");
        String selectSql = sqls[0];
        String paramSql = sqls[1];
        String select = selectSql.substring(selectSql.indexOf(":") + 2);
        String params = paramSql.substring(paramSql.indexOf(":") + 2);
        List<String> paramList = new ArrayList<>();
        while (params.contains("(")) {
            int leftBracket = params.indexOf("(");
            paramList.add(params.substring(0, leftBracket));
            int rightBracket = params.indexOf(")");
            if (rightBracket + 1 == params.length()) {
                break;
            } else {
                params = params.substring(rightBracket + 3);
            }
        }
        int i = 0;
        while (select.contains("?")) {
            String param = paramList.get(i++);
            select = select.replaceFirst("\\?", "'" + param + "'");
        }
        System.out.println(select);
    }
}