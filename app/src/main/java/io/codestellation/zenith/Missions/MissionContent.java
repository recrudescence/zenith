package io.codestellation.zenith.Missions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by David on 11/14/2015.
 */
public class MissionContent {

    public static String infoDatabase = "Placeholder";

    public static List<String> missionTitles = new ArrayList<>();

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
        return new MissionItem(String.valueOf(position), missionTitles.get(position), makeDetails(position));
    }
    private static String makeDetails(int position)
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++)
        {
            builder.append("\nMore detailed information here");
        }
        return builder.toString();
    }

    private static void makeTitle()
    {
        for (int i = 0; i < COUNT; i++);
        missionTitles.add("Make Way for Ducklings");
        return;
    }

}

