package pres.yao.yaogame.host.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author Fahaxiki
 * @ClassName Subscription
 * @Description TODO
 * @Date 2021/3/10
 */
@Data
@Entity
@Table(name = "user_subscription")
public class Subscription implements Serializable {
	/**
	 * @Description: 序列化版本号
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",nullable = false)
	private Integer id;

	/**
	 * @Description: 比赛id
	 */
	@Column(name = "competition_id")
	private int competitionId;
	
	/**
	 * @Description: 用户名,用来关联用户
	 */
	@Column(name = "username")
	private String userName;
}
