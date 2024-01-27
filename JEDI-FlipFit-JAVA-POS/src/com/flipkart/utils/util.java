package com.flipkart.utils;

import com.flipkart.bean.GymCentre;
import com.flipkart.bean.GymOwner;

import java.util.*;
import java.util.stream.Stream;

import static com.flipkart.constants.Constants.*;
import static com.flipkart.constants.Constants.DASHED_LINE;

public class util {

    public static void printList(Iterable<?> list) {
        for (Object element : list) {
            System.out.println(element);
        }
    }

    public static void printGymCentres(List<GymCentre> gymCentres){

        String[][] table = new String[gymCentres.size()+1][6];
        String[][] table_dup = new String[gymCentres.size()+1][6];
        String[] cols = { "CENTRE-ID", "Name", "CITY", "OWNER-ID", "CAPACITY", "IS-APPROVED" };
        for(int i =0;i<6;i++){
            table[0][i] = cols[i];
            table_dup[0][i] = cols[i];
        }
        for(int i=1;i<gymCentres.size()+1;i++){
            table[i][0] = gymCentres.get(i - 1).getGymCentreID();
            table[i][1] = gymCentres.get(i - 1).getGymCenterName();
            table[i][2] = gymCentres.get(i - 1).getCity();
            table[i][3] = String.valueOf(gymCentres.get(i - 1).getOwnerID());
            table[i][4] = String.valueOf(gymCentres.get(i-1).getCapacity());
            if(gymCentres.get(i - 1).getIsApproved() == 1){
                table[i][5] = GREEN_COLOR + "Approved   " + RESET_COLOR;
            }
            else if(gymCentres.get(i - 1).getIsApproved() == 0){
                table[i][5] = RED_COLOR + "No         " + RESET_COLOR;
            }
            else {
                table[i][5] = "Pending   ";
            }

            table_dup[i][0] = gymCentres.get(i - 1).getGymCentreID();
            table_dup[i][1] = gymCentres.get(i - 1).getGymCenterName();
            table_dup[i][2] = gymCentres.get(i - 1).getCity();
            table_dup[i][3] = String.valueOf(gymCentres.get(i - 1).getOwnerID());
            table_dup[i][4] = String.valueOf(gymCentres.get(i-1).getCapacity());
            if(gymCentres.get(i - 1).getIsApproved() == 1){
                table_dup[i][5] =  "Approved   " ;
            }
            else if(gymCentres.get(i - 1).getIsApproved() == 0){
                table_dup[i][5] = "No         ";
            }
            else {
                table_dup[i][5] = "Pending   ";
            }
        }
        tableWithLines(table, table_dup);
    }

    public static void printOwnerList(List<GymOwner> gymOwnerList){

        String[][] table = new String[gymOwnerList.size()+1][5];
        String[][] table_dup = new String[gymOwnerList.size()+1][5];
        String[] cols = { "ID", "Name", "EMAIL-ID", "PAN", "IS-APPROVED" };
        for(int i =0;i<5;i++){
            table[0][i] = cols[i];
            table_dup[0][i] = cols[i];
        }
        for(int i=1;i<gymOwnerList.size()+1;i++){
            table[i][0] = gymOwnerList.get(i - 1).getUserID();
            table[i][1] = gymOwnerList.get(i - 1).getUserName();
            table[i][2] = gymOwnerList.get(i - 1).getEmail();
            table[i][3] = String.valueOf(gymOwnerList.get(i - 1).getPanNumber());
            if(gymOwnerList.get(i - 1).getIsApproved() == 1){
                table[i][4] = GREEN_COLOR + "Approved   " + RESET_COLOR;
            }
            else if(gymOwnerList.get(i - 1).getIsApproved() == 0){
                table[i][4] = RED_COLOR + "No         " + RESET_COLOR;
            }
            else {
                table[i][4] = "Pending   ";
            }

            table_dup[i][0] = gymOwnerList.get(i - 1).getUserID();
            table_dup[i][1] = gymOwnerList.get(i - 1).getUserName();
            table_dup[i][2] = gymOwnerList.get(i - 1).getEmail();
            table_dup[i][3] = String.valueOf(gymOwnerList.get(i - 1).getPanNumber());
            if(gymOwnerList.get(i - 1).getIsApproved() == 1){
                table_dup[i][4] =  "Approved";
            }
            else if(gymOwnerList.get(i - 1).getIsApproved() == 0){
                table_dup[i][4] = "No";
            }
            else {
                table_dup[i][4] = "Pending";
            }
        }
        tableWithLines(table, table_dup);
    }

    public static void printGymCentreList(List<GymCentre> gymCentreList){
//        System.out.println(gymCentreList.size()+"---------");
        String[][] table = new String[gymCentreList.size()+1][6];
        String[][] table_dup = new String[gymCentreList.size()+1][6];
        String[] cols = { "ID", "Center Name", "City", "Capacity", "Price", "IS-APPROVED" };
        for(int i =0;i<6;i++){
            table[0][i] = cols[i];
            table_dup[0][i] = cols[i];
        }
        for(int i=1;i<gymCentreList.size()+1;i++){
            table[i][0] = gymCentreList.get(i - 1).getGymCentreID();
            table[i][1] = gymCentreList.get(i - 1).getGymCenterName();
            table[i][2] = gymCentreList.get(i - 1).getCity();
            table[i][3] = String.valueOf(gymCentreList.get(i - 1).getCapacity());
            table[i][4] = String.valueOf(gymCentreList.get(i - 1).getPrice());
            if(gymCentreList.get(i - 1).getIsApproved() == 1){
                table[i][5] = GREEN_COLOR + "Approved   " + RESET_COLOR;
            }
            else if(gymCentreList.get(i - 1).getIsApproved() == 0){
                table[i][5] = RED_COLOR + "No         " + RESET_COLOR;
            }
            else{
                table[i][5] = "Pending   ";
            }

            table_dup[i][0] = gymCentreList.get(i - 1).getGymCentreID();
            table_dup[i][1] = gymCentreList.get(i - 1).getGymCenterName();
            table_dup[i][2] = gymCentreList.get(i - 1).getCity();
            table_dup[i][3] = String.valueOf(gymCentreList.get(i - 1).getCapacity());
            table_dup[i][4] = String.valueOf(gymCentreList.get(i - 1).getPrice());
            if(gymCentreList.get(i - 1).getIsApproved() == 1){
                table_dup[i][5] = "Approved";
            }
            else if(gymCentreList.get(i - 1).getIsApproved() == 0){
                table_dup[i][5] = "No";
            }
            else{
                table_dup[i][5] = "Pending";
            }
        }

        tableWithLines(table, table_dup);
    }

    public static void tableWithLines(String[][] table, String[][] table_1) {
        /*
         * leftJustifiedRows - If true, it will add "-" as a flag to format string to
         * make it left justified. Otherwise right justified.
         */
        boolean leftJustifiedRows = true;

        /*
         * Calculate appropriate Length of each column by looking at width of data in
         * each column.
         *
         * Map columnLengths is <column_number, column_length>
         */
        Map<Integer, Integer> columnLengths = new HashMap<>();
        Arrays.stream(table_1).forEach(a -> Stream.iterate(0, (i -> i < a.length), (i -> ++i)).forEach(i -> {
            if (columnLengths.get(i) == null) {
                columnLengths.put(i, 0);
            }
            if (columnLengths.get(i) < a[i].length()) {
                columnLengths.put(i, a[i].length());
            }
        }));

        /*
         * Prepare format String
         */
        final StringBuilder formatString = new StringBuilder("");
        String flag = leftJustifiedRows ? "-" : "";
        columnLengths.entrySet().stream().forEach(e -> formatString.append("| %" + flag + e.getValue() + "s "));
        formatString.append("|\n");

        /*
         * Prepare line for top, bottom & below header row.
         */
        String line = columnLengths.entrySet().stream().reduce("", (ln, b) -> {
            String templn = "+-";
            templn = templn + Stream.iterate(0, (i -> i < b.getValue()), (i -> ++i)).reduce("", (ln1, b1) -> ln1 + "-",
                    (a1, b1) -> a1 + b1);
            templn = templn + "-";
            return ln + templn;
        }, (a, b) -> a + b);
        line = line + "+\n";

        /*
         * Print table
         */
        System.out.print(YELLOW_COLOR + line);
        Arrays.stream(table).limit(1).forEach(a -> System.out.printf(formatString.toString(), a));
        System.out.print(line+RESET_COLOR);

        Stream.iterate(1, (i -> i < table.length), (i -> ++i))
                .forEach(a ->
                        System.out.printf(formatString.toString(), table[a])
                );
        System.out.print(line);
    }

    public static String generateNewId(){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
