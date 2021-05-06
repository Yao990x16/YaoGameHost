package pres.yao.yaogame.host.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Fahaxiki
 * @ClassName CompetitionAS
 * @Description TODO
 * @Date 2021/5/6
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompetitionAS {
	private String competition_id;
	private String competition_type;
	private String game_stage;
	private String left_icon;
	private String left_name;
	private int left_score;
	private String right_icon;
	private String right_name;
	private int right_score;
	private String start_time;
	private String msg;

	public CompetitionAS(String msg) {
		this.msg = msg;
	}
}
