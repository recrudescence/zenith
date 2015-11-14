package io.codestellation.zenith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by David on 11/14/2015.
 */
public class MissionContent {

    public static String infoDatabase = "Placeholder";

    public static List<String> MissionTitles = new ArrayList<>();

    public static List<MissionItem> missionBoard = new ArrayList<>();

    public static Map<String, MissionItem> missionBoardMap = new HashMap<>();


    private static final int COUNT = 25;

    static{
        for (int i = 1; i <= COUNT; i++)
        {
            addItem(createMissionItem(i));
        }
    }

    public static class MissionItem
    {
        public String id;
        public String Title;
        public String Details;

        public MissionItem(String id, String Title, String Details)
        {
            this.id = id;
            this.Title= Title;
            this.Details = Details;
        }
    }
    private static void addItem(MissionItem Mission)
    {
        missionBoard.add(Mission);
        missionBoardMap.put(Mission.id, Mission);
    }
    private static MissionItem createMissionItem(int position)
    {
        return new MissionItem(String.valueOf(position), MissionTitles[position], makeDetails(position));
    }
    private static String makeDetails(int position)
    {

    }

}
