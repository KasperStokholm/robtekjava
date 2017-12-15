/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DrawingProgram;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Kasper
 */
public class ImageManipulator {
        private ArrayList<Drawing> drawings = new ArrayList();
        private EdgeDetector edge;
        private Color[][] array;
        private int picturePixelCounter = 0;
        
        public ImageManipulator(String url){
            this.edge = new EdgeDetector(url);
            this.array = this.edge.getGreyscaleArray();
        }
        
        //Array of one's and zero's created from image URL
        private String getOneZeroString(){
            String oneZeroString = "";
            int[][] booleanArray = new int[this.edge.getBufferedImage().getWidth()][this.edge.getBufferedImage().getHeight()];
            for (int i = 0; i < booleanArray.length; i++) {
                for (int j = 0; j < booleanArray[i].length; j++) {
                    if(this.array[i][j].getGreen() == 255 && this.array[i][j].getRed() == 255 && this.array[i][j].getBlue() == 255){
                        oneZeroString = oneZeroString + "0";
                    } else{
                        oneZeroString = oneZeroString + "1";
                    }
                }
            }
            return oneZeroString;
        }
        
        public String getCommandString(){
            String commandString = "";
            String tempString = "";
            String oneZeroString = getOneZeroString();
            int pixelCounter = 0;
            int allCounter = 0;
            int mCount = 0;
            int dCount = 0;
            int check = edge.getBufferedImage().getWidth();          
            
            for(int i = 0; i < oneZeroString.length(); i++){
                this.picturePixelCounter++;
                if(oneZeroString.charAt(i) == '0'){
                    tempString = tempString + "M";
                } else if(oneZeroString.charAt(i) == '1'){
                    tempString = tempString + "D";
                }
            }
            commandString = commandString + "S";
            
            for(int i = 0; i < tempString.length(); i++){
                
                if(pixelCounter == check){
                    if(tempString.charAt(allCounter) == 'D'){
                        dCount++;
                        if(dCount > 0 && dCount < 10){
                            commandString = commandString + "D000" + dCount;
                        } else if(dCount > 9 && dCount < 100){
                            commandString = commandString + "D00" + dCount;
                        } else if(dCount > 99 && dCount < 1000){
                            commandString = commandString + "D0" + dCount;
                        } else if(dCount > 999 && dCount < 10000){
                            commandString = commandString + "D" + dCount;
                        }
                    }
                    commandString = commandString + "N";
                    pixelCounter = 0;
                    mCount = 0;
                    dCount = 0;
                }
                
                if(allCounter < tempString.length()-1){
                    switch (tempString.charAt(i)) {

                        case 'M':
                            allCounter++;
                            if(tempString.charAt(allCounter) == 'D'){
                                if(mCount > 0 && mCount < 10){
                                    commandString = commandString + "M000" + mCount;
                                    mCount = 0;
                                } else if(mCount > 9 && mCount < 100){
                                    commandString = commandString + "M00" + mCount;
                                    mCount = 0;
                                } else if(mCount > 99 && mCount < 1000){
                                    commandString = commandString + "M0" + mCount;
                                    mCount = 0;
                                }
                            }
                            mCount++;
                            pixelCounter++;
                            break;
                        case 'D':
                            allCounter++;
                            if(tempString.charAt(allCounter) == 'M'){
                                if(dCount > 0 && dCount < 10){
                                    commandString = commandString + "D000" + dCount;
                                    dCount = 0;
                                } else if(dCount > 9 && dCount < 100){
                                    commandString = commandString + "D00" + dCount;
                                    dCount = 0;
                                } else if(dCount > 99 && dCount < 1000){
                                    commandString = commandString + "D0" + dCount;
                                    dCount = 0;
                                }
                            }
                            dCount++;
                            pixelCounter++;
                            break;
                        default:
                            break;
                    }
                }
            }
            return commandString;
        }
        
        
//        //Creating gigantic array with D, M, S and N
//        public String getCommandString(){
//            String commandString = "";
//            int commandWordCounter1 = 0;
//            int commandWordCounter0 = 0;
//            int temp = 0;
//            String oneZeroString = getOneZeroString();
//            int check = this.edge.getBufferedImage().getWidth();
//            int pixelCounter = 0;
//
//            for(int i = 0; i < oneZeroString.length(); i++){
//                
//                if(oneZeroString.charAt(i) == '0'){
//                    this.picturePixelCounter++;
//                    pixelCounter++;
//                    if(oneZeroString.charAt(commandWordCounter0 + temp) == '1' || commandWordCounter0 == check){
//                        if(commandWordCounter1 > 0 && commandWordCounter1 < 10){
//                            commandString = commandString + "D" + "000" + commandWordCounter1;
//                            temp = commandWordCounter1 + temp;
//                            commandWordCounter1 = 0;
//                        } else if(commandWordCounter1 > 9 && commandWordCounter1 < 100){
//                            commandString = commandString + "D" + "00" + commandWordCounter1;
//                            temp = commandWordCounter1 + temp;
//                            commandWordCounter1 = 0;
//                        } else if(commandWordCounter1 > 99 && commandWordCounter1 < 1000){
//                            commandString = commandString + "D" + "0" + commandWordCounter1;
//                            temp = commandWordCounter1 + temp;
//                            commandWordCounter1 = 0;
//                        } else if(commandWordCounter1 > 999 && commandWordCounter1 < 10000){
//                            commandString = commandString + "D" + commandWordCounter1;
//                            temp = commandWordCounter1 + temp;
//                            commandWordCounter1 = 0;
//                        } else if (commandWordCounter0 == check){
//                            temp = commandWordCounter0 + temp;
//                            commandWordCounter0 = 0;
//                        }
//                    }
//                    commandWordCounter0++;
//                } else if(oneZeroString.charAt(i) == '1'){
//                    this.picturePixelCounter++;
//                    pixelCounter++;
//                    if(oneZeroString.charAt(commandWordCounter1 + temp) == '0' || commandWordCounter1 == check){
//                        if(commandWordCounter0 > 0 && commandWordCounter0 < 10){
//                            commandString = commandString + "M" + "000" + commandWordCounter0;
//                            temp = commandWordCounter0 + temp;
//                            commandWordCounter0 = 0;
//                        } else if(commandWordCounter0 > 9 && commandWordCounter0 < 100){
//                            commandString = commandString + "M" + "00" + commandWordCounter0;
//                            temp = commandWordCounter0 + temp;
//                            commandWordCounter0 = 0;
//                        } else if(commandWordCounter0 > 99 && commandWordCounter0 < 1000){
//                            commandString = commandString + "M" + "0" + commandWordCounter0;
//                            temp = commandWordCounter0 + temp;
//                            commandWordCounter0 = 0;
//                        } else if(commandWordCounter0 > 999 && commandWordCounter0 < 10000){
//                            commandString = commandString + "M" + commandWordCounter0;
//                            temp = commandWordCounter0 + temp;
//                            commandWordCounter0 = 0;
//                        } else if (commandWordCounter1 == check && commandWordCounter1 > 0 && commandWordCounter1 < 10){
//                            commandString = commandString + "D" + "000" + commandWordCounter1;
//                            commandString = commandString + "SN";
//                            temp = commandWordCounter0 + temp;
//                            commandWordCounter0 = 0;
//                        } else if (commandWordCounter1 == check && commandWordCounter1 > 9 && commandWordCounter1 < 100){
//                            commandString = commandString + "D" + "00" + commandWordCounter1;
//                            commandString = commandString + "SN";
//                            temp = commandWordCounter0 + temp;
//                            commandWordCounter0 = 0;
//                        } else if (commandWordCounter1 == check && commandWordCounter1 > 99 && commandWordCounter1 < 1000){
//                            commandString = commandString + "D" + "0" + commandWordCounter1;
//                            commandString = commandString + "SN";
//                            temp = commandWordCounter0 + temp;
//                            commandWordCounter0 = 0;
//                        } else if (commandWordCounter1 == check && commandWordCounter1 > 999 && commandWordCounter1 < 10000){
//                            commandString = commandString + "D" + commandWordCounter1;
//                            commandString = commandString + "SN";
//                            temp = commandWordCounter0 + temp;
//                            commandWordCounter0 = 0;
//                        }
//                    }
//                    commandWordCounter1++;
//                    
//                }
//                 if(pixelCounter == check){
//                    commandString = commandString + "SN";
//                    pixelCounter = 0;
//                 }
//            }
//            return commandString;
//        }
        
    /**
     * @return the drawings
     */
    public ArrayList<Drawing> getDrawings() {
        int[][] booleanArray = new int[this.edge.getBufferedImage().getWidth()][this.edge.getBufferedImage().getHeight()];
        for (int i = 0; i < booleanArray.length; i++) {
            for (int j = 0; j < booleanArray[i].length; j++) {
                if(this.array[i][j].getGreen() != 255 && this.array[i][j].getRed() != 255 && this.array[i][j].getBlue() != 255){
                    drawings.add(new Drawing(i, j, i, j));
                }
            }
        }
        return drawings;
    }

    public EdgeDetector getEdge() {
        return edge;
    }

    /**
     * @return the counter
     */
    public int getCounter() {
        return picturePixelCounter;
    }
}