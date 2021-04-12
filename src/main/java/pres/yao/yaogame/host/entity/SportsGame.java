package pres.yao.yaogame.host.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author Fahaxiki
 * @ClassName SportsGame
 * @Description TODO
 * @Date 2021/3/10
 */
@Data
@Entity
@Table(name = "sports_game")
public class SportsGame implements Serializable {
	/**
	 * @Description: 序列化版本号
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",nullable = false)
	private Integer id;
	
	/**
	 * @Description: 体育项目种类名称
	 */
	@Column(name = "game_name", unique = true)
	private String gameName;
}
