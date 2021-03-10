package pres.yao.yaogame.host.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author Fahaxiki
 * @ClassName ESportsTeam
 * @Description TODO
 * @Date 2021/3/10
 */
@Data
@Entity
@Table(name = "esports_team")
public class ESportsTeam implements Serializable {
	/**
	 * @Description: 序列化版本号
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id",nullable = false)
	private Integer id;

	/**
	 * @Description: 队伍id
	 */
	@Column(name = "team_id")
	private Integer teamId;

	/**
	 * @Description: 队伍名称
	 */
	@Column(name = "tean_name")
	private String teamName;

	/**
	 * @Description: 队伍别称
	 */
	@Column(name = "team_sub_name")
	private String teamSubName;

	/**
	 * @Description: 队伍LogoURL
	 */
	@Column(name = "team_logo_url")
	private String teamLogoUrl;
}
