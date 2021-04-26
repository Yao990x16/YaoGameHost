package pres.yao.yaogame.host.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author Fahaxiki
 * @ClassName NBAForecast
 * @Description TODO
 * @Date 2021/4/26
 */
@Data
@Entity
@Table(name = "nba_forecast")
public class NBAForecast implements Serializable {
	/**
	 * @Description: 序列化版本号
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",nullable = false)
	private Integer id;

	/**
	 * @Description: 开始时间
	 */
	@Column(name = "start_time",nullable = false)
	private String startTime;

	@Column(name = "win_team", nullable = false)
	private String winTeam;

	@Column(name = "lose_team", nullable = false)
	private String loseTeam;

	@Column(name = "probability", nullable = false)
	private String probability;

}
