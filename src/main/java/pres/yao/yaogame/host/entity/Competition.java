package pres.yao.yaogame.host.entity;

import lombok.Data;
import pres.yao.yaogame.host.entity.meiju.Type;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author Fahaxiki
 * @ClassName Competition
 * @Description TODO
 * @Date 2021/3/10
 */
@Data
@Entity
@Table(name = "competition")
public class Competition implements Serializable {
	/**
	 * @Description: 序列化版本号
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id",nullable = false)
	private Integer id;

	/**
	 * @Description: 比赛描述/轮次
	 */
	@Column(name = "game_stage")
	private String gameStage;
	
	/**
	 * @Description: 左边/主场队伍id
	 */
	@Column(name = "left_id")
	private Integer leftId;
	
	/**
	 * @Description: 左边/主场队伍名称
	 */
	@Column(name = "left_name")
	private String leftName;
	
	/**
	 * @Description: 左边/主场队伍分数
	 */
	@Column(name = "left_score")
	private int leftScore;
	
	/**
	 * @Description: 右边/客场队伍id
	 */
	@Column(name = "right_id")
	private Integer rightId;
	
	/**
	 * @Description: 右边/客场队伍名称
	 */
	@Column(name = "right_name")
	private String rightName;

	/**
	 * @Description: 右边/客场队伍比分
	 */
	@Column(name = "right_score")
	private int rightScore;

	/**
	 * @Description: 赛事开始时间
	 */
	@Column(name = "start_time")
	private String startTime;

	/**
	 * @Description: 比赛类型,电子竞技比赛/传统体育比赛
	 */
	@Column(name = "competition_type")
	@Enumerated(EnumType.STRING)
	private Type competitionType;
}
