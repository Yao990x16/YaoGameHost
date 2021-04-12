package pres.yao.yaogame.host.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author Fahaxiki
 * @ClassName SportsMatch
 * @Description TODO
 * @Date 2021/3/10
 */
@Data
@Entity
@Table(name = "sports_match")
public class SportsMatch implements Serializable {
	/**
	 * @Description: 序列化版本号
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",nullable = false)
	private Integer id;

	/**
	 * @Description: 联赛id
	 */
	@Column(name = "match_id", unique = true)
	private Integer matchId;

	/**
	 * @Description: 联赛名称
	 */
	@Column(name = "match_name")
	private String matchName;

	/**
	 * @Description: 联赛LogoURL
	 */
	@Column(name = "match_logo_url")
	private String matchLogoUrl;
}
