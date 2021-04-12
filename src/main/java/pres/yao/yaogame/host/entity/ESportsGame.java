package pres.yao.yaogame.host.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author Fahaxiki
 * @ClassName ESportsGame
 * @Description TODO
 * @Date 2021/3/10
 */
@Data
@Entity
@Table(name = "esports_game")
public class ESportsGame implements Serializable {
	/**
	 * @Description: 序列化版本号
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",nullable = false)
	private Integer id;

	/**
	 * @Description: 游戏名称
	 */
	@Column(name = "game_name",unique = true)
	private String gameName;

	/**
	 * @Description: 游戏别称
	 */
	@Column(name = "game_sub_name")
	private String gameSubName;

	/**
	 * @Description: 游戏LogoURL
	 */
	@Column(name = "game_logo_url")
	private String gameLogoUrl;
}
