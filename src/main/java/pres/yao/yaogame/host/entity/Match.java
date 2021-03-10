package pres.yao.yaogame.host.entity;

import lombok.Data;
import pres.yao.yaogame.host.entity.meiju.Type;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author Fahaxiki
 * @ClassName Match
 * @Description TODO
 * @Date 2021/3/10
 */
@Data
@Entity
@Table(name = "match")
public class Match implements Serializable {
	/**
	 * @Description: 序列化版本号
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id",nullable = false)
	private Integer id;

	/**
	 * @Description: 联赛名称
	 */
	@Column(name = "match_name")
	private String matchName;

	@Column(name = "match_type")
	@Enumerated(EnumType.STRING)
	private Type matchType;
}
