package pres.yao.yaogame.host.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author Fahaxiki
 * @ClassName User
 * @Description TODO
 * @Date 2021/3/9
 */
@Data
@Entity
@Table(name = "user")
public class User implements Serializable {
	/**
	 * @Description: 序列化版本号
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id",nullable = false)
	private Integer id;

	/**
	 * @Description: 用户名
	 */
	@Column(name = "username",nullable = false)
	private String username;

	/**
	 * @Description: 密码
	 */
	@Column(name = "password",nullable = false)
	private String password;

	/**
	 * @Description: 邮箱
	 */
	@Column(name = "email",nullable = false)
	private String email;
}
