package pres.yao.yaogame.host.entity;

import lombok.Data;
import pres.yao.yaogame.host.entity.meiju.Type;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author Fahaxiki
 * @ClassName Game
 * @Description TODO
 * @Date 2021/3/10
 */
@Data
@Entity
@Table(name = "game")
public class Game implements Serializable {
	/**
	 * @Description: 序列化版本号
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id",nullable = false)
	private Integer id;
	
	/**
	 * @Description: 游戏/运动名称
	 */
	@Column(name = "game_name")
	private String gameName;
	
	/**
	 * @Description: 类型
	 */
	@Column(name = "game_type")
	@Enumerated(EnumType.STRING)
	private Type gameType;
}
