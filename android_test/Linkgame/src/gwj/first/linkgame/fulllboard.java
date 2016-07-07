package gwj.first.linkgame;
import java.util.ArrayList;
import java.util.List;



public class fulllboard extends AbstractBorard {

	@Override
	protected List<piece> createpiece(gameconf config, piece[][] pieces) {
		List<piece> noNullPieces = new ArrayList<piece>();
		for (int i = 0; i < pieces.length-1; i++) {
			
			for (int j = 0; j < pieces[i].length-1; j++) {
				piece piece = new piece(i, j);
				noNullPieces.add(piece);
			}
		}
		return noNullPieces;
	}

}
