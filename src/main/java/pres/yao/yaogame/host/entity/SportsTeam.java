package pres.yao.yaogame.host.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author Fahaxiki
 * @ClassName SportsTeam
 * @Description TODO
 * @Date 2021/3/10
 */
@Data
@Entity
@Table(name = "sports_team")
public class SportsTeam implements Serializable {
	/**
	 * @Description: 序列化版本号
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",nullable = false)
	private Integer id;

	/**
	 * @Description: 队伍Id
	 */
	@Column(name = "team_id", unique = true)
	private Integer teamId;

	/**
	 * @Description: 队伍名称
	 */
	@Column(name = "team_name")
	private String teamName;

	/**
	 * @Description: 队伍LogoURL
	 */
	@Column(name = "team_logo_url")
	private String teamLogoUrl;
}
