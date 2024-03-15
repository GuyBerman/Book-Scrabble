package test;
import java.util.ArrayList;


public class Board {
    private static Board board =null;
    private Tile[][] current_tiles;
    private final String [][]score = {
            {"w3",null,null,"l2",null,null,null,"w3",null,null,null,"l2",null,null,"w3"},
            {null,"w2",null,null,null,"l3",null,null,null,"l3",null,null,null,"w2",null},
            {null,null,"w2",null,null,null,"l2",null,"l2",null,null,null,"w2",null,null},
            {"l2",null,null,"w2",null,null,null,"l2",null,null,null,"w2",null,null,"l2"},
            {null,null,null,null,"w2",null,null,null,null,null,"w2",null,null,null,null},
            {null,"l3",null,null,null,"l3",null,null,null,"l3",null,null,null,"l3",null},
            {null,null,"l2",null,null,null,"l2",null,"l2",null,null,null,"l2",null,null},
            {"w3",null,null,"l2",null,null,null,"s",null,null,null,"l2",null,null,"w3"},
            {null,null,"l2",null,null,null,"l2",null,"l2",null,null,null,"l2",null,null},
            {null,"l3",null,null,null,"l3",null,null,null,"l3",null,null,null,"l3",null},
            {null,null,null,null,"w2",null,null,null,null,null,"w2",null,null,null,null},
            {"l2",null,null,"w2",null,null,null,"l2",null,null,null,"w2",null,null,"l2"},
            {null,null,"w2",null,null,null,"l2",null,"l2",null,null,null,"w2",null,null},
            {null,"w2",null,null,null,"l3",null,null,null,"l3",null,null,null,"w2",null},
            {"w3",null,null,"l2",null,null,null,"w3",null,null,null,"l2",null,null,"w3"},
    };
    private Board(){
        current_tiles = new Tile[15][15];
    }

    public static Board getBoard(){
        if(board == null){
            board = new Board();
        }
        return board;
    }
    public Tile[][] getTiles(){
        return this.current_tiles;
    }
    public boolean boardLegal(Word word){
        boolean is_side = false;

        if(word.getRow()<0 || word.getRow()>14||word.getCol()<0 || word.getCol()>14){
            return false;
        }
        if(current_tiles[7][7]==null){
            if(word.isVertical()&&word.getRow()<=7&&word.getRow()+word.getTiles().length>=7&&word.getRow()+word.getTiles().length<=14&&word.getCol()==7){
                return true;
            }
            if(!word.isVertical()&&word.getCol()<=7&&word.getCol()+word.getTiles().length>=7&&word.getCol()+word.getTiles().length<=14&&word.getRow()==7){
                return true;
            }
            return false;
        }


        for (int i = 0; i <word.getTiles().length ; i++) {
            if(word.getTiles()[i]==null){
                is_side=true;
                continue;
            }
            if (word.isVertical()){

                if (word.getRow()+i>14){
                    return false;
                }
                if(current_tiles[word.getRow()+i][word.getCol()]!=null&&!(current_tiles[word.getRow()+i][word.getCol()].letter==word.getTiles()[i].letter)){
                    return false;
                }
                if((word.getCol()+1<15&&current_tiles[word.getRow()+i][word.getCol()+1]!=null)||(word.getCol()-1>=0&&current_tiles[word.getRow()+i][word.getCol()-1]!=null)){
                    is_side=true;
                }
                if((i==0&&word.getRow()-1>=0&&current_tiles[word.getRow()-1][word.getCol()]!=null)||(i==word.getTiles().length-1&&word.getRow()+1<15&&current_tiles[word.getRow()+1][word.getCol()]!=null)){
                    is_side=true;
                }
            }
            else {
                if (word.getCol()+i>14){
                    return false;
                }
                if(current_tiles[word.getRow()][word.getCol()+i]!=null&&!(current_tiles[word.getRow()][word.getCol()+i].letter==word.getTiles()[i].letter)){
                    return false;
                }
                if((word.getRow()+1<15&&current_tiles[word.getRow()+1][word.getCol()+i]!=null)||(word.getRow()-1>=0&&current_tiles[word.getRow()-1][word.getCol()+i]!=null)){
                    is_side=true;
                }
                if((i==0&&word.getCol()-1>=0&&current_tiles[word.getRow()][word.getCol()-1]!=null)||(i==word.getTiles().length-1&&word.getCol()+1<15&&current_tiles[word.getRow()][word.getCol()+1]!=null)){
                    is_side=true;
                }
            }

        }
        return is_side;
    }
    public boolean dictionaryLegal(Word word){
        return true;
    }
    public ArrayList<Word> getWords(Word word){
        ArrayList<Word>words = new ArrayList<Word>();
        int j=0,row=0,col=0;



        words.add(word);
        for (int i = 0; i <word.getTiles().length ; i++) {
            ArrayList<Tile>tiles = new ArrayList<Tile>();
            if(word.isVertical()){
                if((word.getCol()-1>=0&&current_tiles[word.getRow()-i][word.getCol()-1]==null)&&(word.getCol()+1<15&&current_tiles[word.getRow()-i][word.getCol()+1]==null)){
                    continue;
                }
                if((word.getCol()-1>=0&&current_tiles[word.getRow()-i][word.getCol()-1]==null)&&(word.getCol()+1>14)){
                    continue;
                }
                if((word.getCol()+1<15&&current_tiles[word.getRow()-i][word.getCol()+1]==null)&&(word.getCol()-1<0)){
                    continue;
                }
                if(current_tiles[word.getRow()-i][word.getCol()]!=null){
                    continue;
                }
                row=word.getRow()-i;
                tiles.add(word.getTiles()[i]);

                while(word.getCol()-j-1>0&&current_tiles[word.getRow()-i][word.getCol()-j-1]!=null){
                    if(!(current_tiles[word.getRow()][word.getCol()+1]!=null ||current_tiles[word.getRow()][word.getCol()-1]!=null )){
                        col=word.getCol()-j-1;


                    }
                    tiles.add(0,current_tiles[word.getRow()-i][word.getCol()-j-1]);
                    j++;
                }
                j=0;
                while(word.getCol()+j+1<15&&current_tiles[word.getRow()-i][word.getCol()+j+1]!=null){
                    tiles.add(current_tiles[word.getRow()-i][word.getCol()+j+1]);
                    j++;
                }
                j=0;
            }
            else{
                if((word.getRow()-1>=0&&current_tiles[word.getRow()-1][word.getCol()+i]==null)&&(word.getRow()+1<15&&current_tiles[word.getRow()+1][word.getCol()+i]==null)){
                    continue;
                }
                if((word.getRow()-1>=0&&current_tiles[word.getRow()-1][word.getCol()+i]==null)&&(word.getRow()+1>14)){
                    continue;
                }
                if((word.getRow()+1<15&&current_tiles[word.getRow()+1][word.getCol()+i]==null)&&(word.getRow()-1<0)){
                    continue;
                }
                if(current_tiles[word.getRow()][word.getCol()+i]!=null){
                    continue;
                }
                col = word.getCol()+i;
                tiles.add(word.getTiles()[i]);
                while(word.getRow()-j-1>0&&current_tiles[word.getRow()-j-1][word.getCol()+i]!=null){
                    if(!(current_tiles[word.getRow()+1][word.getCol()]!=null ||current_tiles[word.getRow()-1][word.getCol()]!=null )){
                        row=word.getRow()-j-1;

                    }
                    tiles.add(0,current_tiles[word.getRow()-j-1][word.getCol()+i]);
                    j++;
                }
                j=0;

                while(word.getRow()+j+1<15&&current_tiles[word.getRow()+j+1][word.getCol()+i]!=null){
                    tiles.add(current_tiles[word.getRow()+j+1][word.getCol()+i]);
                    j++;
                }
                j=0;
            }
            Tile[]tilelist = new Tile[tiles.size()];
            tilelist = tiles.toArray(tilelist);
            words.add(new Word(tilelist,row,col,!word.isVertical()));

        }


        return words;
    }
    public int getScore(Word word){
        int word_score = 0,mult=0,letter_score=0;
        for (int i = 0; i <word.getTiles().length ; i++) {

            if(word.isVertical()){
                if(word.getTiles()[i]!=null){
                    letter_score = word.getTiles()[i].score;
                }
                else{
                    letter_score=this.current_tiles[word.getRow()+i][word.getCol()].score;
                }
                if(this.score[word.getRow()+i][word.getCol()]!=null){
                    switch (this.score[word.getRow()+i][word.getCol()].charAt(0)){
                        case 'w':
                            mult+=Character.getNumericValue(this.score[word.getRow()+i][word.getCol()].charAt(1));
                            word_score+=letter_score;
                            break;
                        case 'l':
                            word_score+=letter_score*Character.getNumericValue(this.score[word.getRow()+i][word.getCol()].charAt(1));
                            break;
                        case 's':
                            if(this.current_tiles[7][7]==null){
                                mult+=2;
                            }

                            word_score+=letter_score;
                            break;
                    }
                }
                else{
                    word_score+=letter_score;
                }
            }
            else{
                if(word.getTiles()[i]!=null){
                    letter_score = word.getTiles()[i].score;
                }
                else{
                    letter_score=this.current_tiles[word.getRow()][word.getCol()+i].score;
                }
                if(this.score[word.getRow()][word.getCol()+i]!=null){
                    switch (this.score[word.getRow()][word.getCol()+i].charAt(0)){
                        case 'w':
                            mult+=Character.getNumericValue(this.score[word.getRow()][word.getCol()+i].charAt(1));
                            word_score+=letter_score;
                            break;
                        case 'l':
                            word_score+=letter_score*Character.getNumericValue(this.score[word.getRow()][word.getCol()+i].charAt(1));
                            break;
                        case 's':
                            if(this.current_tiles[7][7]==null){
                                mult+=2;
                            }
                            word_score+=letter_score;
                            break;
                    }
                }
                else{
                    word_score+=letter_score;
                }
            }
        }
        if(mult>0){
            word_score=word_score*mult;
        }
        return word_score;
    }
    public int tryPlaceWord(Word word){
        int total_score=0;
        if(!this.boardLegal(word)){
            return 0;
        }
        ArrayList<Word>all_words = this.getWords(word);

        for (Word allWord : all_words) {
            if (!this.dictionaryLegal(allWord)) {
                return 0;
            }
            total_score+=this.getScore(allWord);
        }
        for (int i = 0; i <word.getTiles().length ; i++) {
            if(word.getTiles()[i]==null){
                continue;
            }
            if(word.isVertical()){
                current_tiles[word.getRow()+i][word.getCol()] = word.getTiles()[i];
            }
            else {
                current_tiles[word.getRow()][word.getCol()+i] = word.getTiles()[i];
            }
        }
        return total_score;
    }


}
