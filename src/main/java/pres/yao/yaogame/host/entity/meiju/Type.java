package pres.yao.yaogame.host.entity.meiju;


import lombok.*;

/**
 * @author Fahaxiki
 */
@NoArgsConstructor
@AllArgsConstructor
public enum Type {
	/**
	 * @Description: 赛事类型
	 */
	电子竞技("电子竞技"),
	传统体育("传统体育");

	@Getter
	@Setter
	private String type;
}
