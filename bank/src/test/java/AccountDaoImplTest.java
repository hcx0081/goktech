import com.goktech.utils.DpUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Connection;

/**
 * {@code @description:}
 */
class AccountDaoImplTest {
    public void addAccount(long cardId, String password, BigDecimal balance) {
        Connection connection = null;
        try {
            connection = DpUtils.getConnectionByDruid();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "insert into account(cardId,password,balance) values (?,?,?)";
            queryRunner.update(connection, sql, cardId, password, balance);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        DpUtils.closeResourceByDBUtils(null, null, connection);
    }
    
    @Test
    public void t() {
        
        addAccount(1234, "123", BigDecimal.valueOf(0));
        
    }
}