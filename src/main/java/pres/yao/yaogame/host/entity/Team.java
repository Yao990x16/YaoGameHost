package pres.yao.yaogame.host.entity;

import lombok.Data;
import pres.yao.yaogame.host.entity.meiju.Type;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Yao
 * @ClassName Team
 * @Description TODO
 * @Date 2021/3/9 12:25
 */
@Data
@Entity
@Table(name = "team")
public class Team implements Serializable {
	/**
	 * @Description: 序列化版本号
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",nullable = false)
	private Integer id;
	
	/**
	 * @Description: 队伍名称
	 */
	@Column(name = "team_name", unique = true)
	private String teamName;

	@Column(name = "team_type")
	@Enumerated(EnumType.STRING)
	private Type teamType;
}
