package spring.domain;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class MemberDao {

    private JdbcTemplate jdbcTemplate;

    public MemberDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Member selectByEmail(String email) {
        List<Member> results = jdbcTemplate.query(
                "select * from Member where EMAIL = ?",
                new MemberRowMapper(),
                email
        );

        return results.isEmpty() ? null : results.get(0);
    }

    public void insert(Member member) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection con) -> {
            PreparedStatement pstmt = con.prepareStatement(
                    "insert into Member (EMAIL, PASSWORD, NAME, REGDATE) VALUES (?,?,?,?)",
                    new String[]{"ID"});
            pstmt.setString(1, member.getEmail());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getName());
            pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
            return pstmt;
        }, keyHolder);
        Number keyValue = keyHolder.getKey();
        member.setId(keyValue.longValue());
    }

    public void update(Member member) {
        jdbcTemplate.update(
                (Connection con)->{
                        PreparedStatement pstmt = con.prepareStatement("update Member set NAME=?, PASSWORD=? where EMAIL=?");
                        pstmt.setString(1, member.getName());
                        pstmt.setString(2, member.getPassword());
                        pstmt.setString(3, member.getEmail());
                        return pstmt;
                    }
        );
    }

    public List<Member> selectAll() {
        List<Member> results = jdbcTemplate.query(
                "select * from Member",
                new MemberRowMapper()
        );
        return results;
    }

    public int count() {
        Integer count = jdbcTemplate.queryForObject("select count(*) from Member", Integer.class);
        return count;
    }
}