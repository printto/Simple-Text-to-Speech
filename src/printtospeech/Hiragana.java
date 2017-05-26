package printtospeech;

import java.util.ArrayList;
import java.util.List;

public class Hiragana {

	/**
	 * Check if it's Japanese
	 * @param c
	 * @return boolean of checking Japanese character.
	 */
	public boolean isHiragana(final char c)
	{
		return (Character.UnicodeBlock.of(c)==Character.UnicodeBlock.HIRAGANA) || (Character.UnicodeBlock.of(c)==Character.UnicodeBlock.KATAKANA);
	}

	private boolean isKatakana(final char c)
	{
		return (Character.UnicodeBlock.of(c)==Character.UnicodeBlock.KATAKANA);
	}

	/**
	 * Convert Hiragana sentence to Romaji array.
	 * @param sentence
	 * @return Array of Romaji words.
	 */
	public String[] toRomajiArray(String sentence){
		String[] hiraWords = sentence.split("");
		List<String> romaWords = new ArrayList<String>();
		int countRoma = -1;
		for(int i = 0 ; i < hiraWords.length ; i++){
			if(hiraWords[i].equals("は") || hiraWords[i].equals("へ") || hiraWords[i].equals("を")){
				if(i < hiraWords.length - 1){
					if(hiraWords[i - 1].equals(" ") && hiraWords [i + 1].equals(" ")){
						switch(hiraWords[i]){
						case "は":
							romaWords.add("wa");
							break;
						case "へ":
							romaWords.add("e");
							break;
						case "を":
							romaWords.add("o");
							break;
						}
					}
				}
				else{
					romaWords.add(hiraganaToRomaji(hiraWords[i]));
				}
				countRoma++;
			}
			else if(!hiraWords[i].equals("ぁ") && !hiraWords[i].equals("ぃ") && !hiraWords[i].equals("ぅ") && !hiraWords[i].equals("ぇ") && !hiraWords[i].equals("ぉ") && !hiraWords[i].equals("ゃ") && !hiraWords[i].equals("ゅ") && !hiraWords[i].equals("ょ") && !hiraWords[i].equals("っ")  && !hiraWords[i].equals("ャ")   && !hiraWords[i].equals("ュ")   && !hiraWords[i].equals("ョ") && !hiraWords[i].equals("ァ") && !hiraWords[i].equals("ィ") && !hiraWords[i].equals("ゥ") && !hiraWords[i].equals("ェ") && !hiraWords[i].equals("ォ") && !hiraWords[i].equals("ヮ")){
				romaWords.add(hiraganaToRomaji(hiraWords[i]));
				countRoma++;
			}
			else if(i > 0){
				if(romaWords.get(countRoma).charAt(1) == 'h' && !hiraWords[i].equals("っ")){
					romaWords.add(romaWords.get(countRoma - 1).substring(0 , (romaWords.get(countRoma - 1).length() - 2)) + hiraganaToRomaji(hiraWords[i]).charAt(hiraganaToRomaji(hiraWords[i]).length() - 1));
				}
				else if(!hiraWords[i].equals("っ")){
					romaWords.add(romaWords.get(countRoma - 1).substring(0 , (romaWords.get(countRoma - 1).length() - 2)) + hiraganaToRomaji(hiraWords[i]));
				}
				romaWords.remove(countRoma);
			}
		}
		String[] romaArray = new String[romaWords.size()];
		romaWords.toArray(romaArray);
		return romaArray;
	}

	/**
	 * Translate Hiragana character to Romaji.
	 * @param word
	 * @return
	 */
	private String hiraganaToRomaji(String word){
		switch(word){
		case "ア":
		case "ァ":
		case "ぁ":
		case "あ":
			return "a";
		case "ィ":
		case "イ":
		case "ぃ":
		case "い":
			return "i";
		case "ゥ":
		case "ウ":
		case "ぅ":
		case "う":
			return "u";
		case "ェ":
		case "エ":
		case "ぇ":
		case "え":
			return "e";
		case "ォ":
		case "オ":
		case "ぉ":
		case "お":
			return "o";
		case "カ":
		case "か":
			return "ka";
		case "キ":
		case "き":
			return "ki";
		case "ク":
		case "く":
			return "ku";
		case "ケ":
		case "け":
			return "ke";
		case "コ":
		case "こ":
			return "ko";
		case "ガ":
		case "が":
			return "ga";
		case "ギ":
		case "ぎ":
			return "gi";
		case "グ":
		case "ぐ":
			return "gu";
		case "ゲ":
		case "げ":
			return "ge";
		case "ゴ":
		case "ご":
			return "go";
		case "サ":
		case "さ":
			return "sa";
		case "ザ":
		case "ざ":
			return "za";
		case "シ":
		case "し":
			return "shi";
		case "ジ":
		case "じ":
			return "ji";
		case "ス":
		case "す":
			return "su";
		case "ズ":
		case "ず":
			return "zu";
		case "セ":
		case "せ":
			return "se";
		case "ゼ":
		case "ぜ":
			return "ze";
		case "ソ":
		case "そ":
			return "so";
		case "ゾ":
		case "ぞ":
			return "zo";
		case "タ":
		case "た":
			return "ta";
		case "ダ":
		case "だ":
			return "da";
		case "チ":
		case "ち":
			return "chi";
		case "ヂ":
		case "ぢ":
			return "ji";
		case "ツ":
		case "つ":
			return "tsu";
		case "ヅ":
		case "づ":
			return "dzu";
		case "テ":
		case "て":
			return "te";
		case "デ":
		case "で":
			return "de";
		case "ト":
		case "と":
			return "to";
		case "ド":
		case "ど":
			return "do";
		case "ナ":
		case "な":
			return "na";
		case "ニ":
		case "に":
			return "ni";
		case "ヌ":
		case "ぬ":
			return "nu";
		case "ネ":
		case "ね":
			return "ne";
		case "ノ":
		case "の":
			return "no";
		case "ハ":
		case "は":
			return "ha";
		case "バ":
		case "ば":
			return "ba";
		case "パ":
		case "ぱ":
			return "pa";
		case "ヒ":
		case "ひ":
			return "hi";
		case "ビ":
		case "び":
			return "bi";
		case "ピ":
		case "ぴ":
			return "pi";
		case "フ":
		case "ふ":
			return "fu";
		case "ブ":
		case "ぶ":
			return "bu";
		case "プ":
		case "ぷ":
			return "pu";
		case "ヘ":
		case "へ":
			return "he";
		case "ベ":
		case "べ":
			return "be";
		case "ペ":
		case "ぺ":
			return "pe";
		case "ホ":
		case "ほ":
			return "ho";
		case "ボ":
		case "ぼ":
			return "bo";
		case "ポ":
		case "ぽ":
			return "po";
		case "マ":
		case "ま":
			return "ma";
		case "ミ":
		case "み":
			return "mi";
		case "ム":
		case "む":
			return "mu";
		case "メ":
		case "め":
			return "me";
		case "モ":
		case "も":
			return "mo";
		case "ヤ":
		case "や":
		case "ャ":
		case "ゃ":
			return "ya";
		case "ユ":
		case "ゆ":
		case "ュ":
		case "ゅ":
			return "yu";
		case "ョ":
		case "ヨ":
		case "よ":
		case "ょ":
			return "yo";
		case "ラ":
		case "ら":
			return "ra";
		case "リ":
		case "り":
			return "ri";
		case "ル":
		case "る":
			return "ru";
		case "レ":
		case "れ":
			return "re";
		case "ロ":
		case "ろ":
			return "ro";
		case "ワ":
		case "ヮ":
		case "わ":
			return "wa";
		case "を":
			return "wo";
		case "ン":
		case "ん":
			return "n";
		default:
			return word;
		}
	}

}
