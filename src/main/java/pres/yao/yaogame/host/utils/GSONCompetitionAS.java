package pres.yao.yaogame.host.utils;

import pres.yao.yaogame.host.entity.Competition;
import pres.yao.yaogame.host.entity.CompetitionAS;
import pres.yao.yaogame.host.entity.ESportsTeam;
import pres.yao.yaogame.host.entity.SportsTeam;
import pres.yao.yaogame.host.service.ESportsTeamService;
import pres.yao.yaogame.host.service.SportsTeamService;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Fahaxiki
 * @ClassName GSONCompetitionAS
 * @Description TODO
 * @Date 2021/5/6
 */
public class GSONCompetitionAS {
	
	public static String getTeamUrl(Competition competition, String team,
									ESportsTeamService esportsTeamService,
									SportsTeamService sportsTeamService) {
		int leftId = competition.getLeftId();
		int rightId = competition.getRightId();
		String type = competition.getCompetitionType().getType();
		String leftIcon, rightIcon;
		if ("电子竞技".equals(type)) {
			ESportsTeam leftTeam = esportsTeamService.findByTeamName(competition.getLeftName());
			if (leftTeam == null) {
				leftIcon = "";
			} else {
				leftIcon = leftTeam.getTeamLogoUrl();
			}
			ESportsTeam rightTeam = esportsTeamService.findByTeamName(competition.getRightName());
			if (rightTeam == null) {
				rightIcon = "";
			} else {
				rightIcon = rightTeam.getTeamLogoUrl();
			}
		} else {
			SportsTeam leftTeam = sportsTeamService.findByTeamId(leftId);
			if (leftTeam == null) {
				leftIcon = "";
			} else {
				leftIcon = leftTeam.getTeamLogoUrl();
			}
			SportsTeam rightTeam = sportsTeamService.findByTeamId(rightId);
			if (rightTeam == null) {
				rightIcon = "";
			} else {
				rightIcon = rightTeam.getTeamLogoUrl();
			}
		}
		return "left".equals(team) ? leftIcon : rightIcon;
	}

	public static CompetitionAS getCompetitionAS(Competition competition,
												 ESportsTeamService esportsTeamService,
												 SportsTeamService sportsTeamService){
			return new CompetitionAS(
					competition.getCompetitionId(),
					competition.getCompetitionType().getType(),
					competition.getGameStage(),
					getTeamUrl(competition, "left",esportsTeamService,sportsTeamService),
					competition.getLeftName(),
					competition.getLeftScore(),
					getTeamUrl(competition, "right",esportsTeamService,sportsTeamService),
					competition.getRightName(),
					competition.getRightScore(),
					competition.getStartTime(),
					"OK"
			);
	}

	public static List<CompetitionAS> getCompetitionASList(List<Competition> competitionList,
														   ESportsTeamService esportsTeamService,
														   SportsTeamService sportsTeamService) {
		List<CompetitionAS> result = new ArrayList<CompetitionAS>();
		if(competitionList.size()!=0){
			for(Competition competition : competitionList){
				result.add(getCompetitionAS(competition,esportsTeamService,sportsTeamService));
			}
		}else{
			result.add(new CompetitionAS("今天没有比赛"));
		}
		return result;
	}
}
