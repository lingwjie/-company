package gwj.first.linkgame;

import java.util.List;

public abstract class AbstractBorard {
	protected abstract List<piece> createpiece(gameconf config , piece[][] pieces);
	public piece[][] create(gameconf config)
	{
		piece[][] pieces = new piece[config.getx()][config.gety()];
		List<piece> noNullPieces = createpiece(config, pieces);
		List<pieceImage> playImages = imageUtil.getplayimage(config.getContext(),noNullPieces);
		int imageWidth = playImages.get(0).getImage().getWidth();
		int imageHeigh = playImages.get(0).getImage().getHeight();
		for (int i = 0; i < noNullPieces.size(); i++) {
			piece piece = noNullPieces.get(i);
			piece.setImage(playImages.get(i));
			piece.setBeginX(piece.getIndexX()*imageWidth+config.getbeginImagex());
			piece.setBeginY(piece.getIndexY()*imageHeigh + config.getbeginImagey());
			pieces[piece.getIndexX()][piece.getIndexY()] = piece;
		}
		return pieces ;
		
	}

}
