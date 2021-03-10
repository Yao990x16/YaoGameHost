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
	ESports("电子竞技"),
	Sports("传统体育");

	@Getter
	@Setter
	private String type;
}
