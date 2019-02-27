package org.study.spring.service.board;

public class Paging {

	public static final int PAGE_SCALE = 5;
	public static final int BLOCK_SCALE = 5;
	
	public int curPage; // 현재 페이지
	public int prevPage; // 이전 페이지
	public int nextPage; 
	public int totPage; 
	public int totBlock; //전체 페이지 블록 갯수
	public int curBlock; 
	public int prevBlock; 
	public int nextBlock; 
	
	private int pageBegin; // #{start} 
	private int pageEnd; // #{end}
	
	private int blockBegin;
	private int blockEnd;
	
	public Paging(int count, int curPage) {
		curBlock = 1; // 현재 페이지 블록 번호
		this.curPage = curPage;
		setTotPage(count);
		setPageRange();
		setTotBlock();
		setBlockRange();
	}
	
	public void setBlockRange() {
		curBlock = (int) (Math.ceil((curPage - 1) / BLOCK_SCALE) + 1); // 현재 페이지가 몇 번째 블록에 속하는지
		blockBegin = (curBlock - 1)*BLOCK_SCALE + 1; // 현재 페이지 블록의 시작, 끝 번호 계산
		blockEnd = blockBegin + BLOCK_SCALE - 1; // 마지막 페이지 번호가 범위를 초과하지 않도록 처리
		
		if (blockEnd > totPage) { // [이전]을 클릭했을 떄 이동할 페이지 번호
			blockEnd = totPage;
		}
		// [이전]을 클릭했을 때 이동할 페이지 번호
		prevPage = (curBlock == 1) ? 1:(curBlock - 1)*BLOCK_SCALE;
		// [다음]을 클릭했을 때 이동할 페이지 번호
		nextPage = curBlock > totBlock ? (curBlock * BLOCK_SCALE) : (curBlock * BLOCK_SCALE) + 1;
		
		if (nextPage >= totPage) {
			nextPage = totPage;
		}
	}
	
	public void setTotPage(int count) {
		totPage = (int) Math.ceil(count * 1.0 / PAGE_SCALE);
	}
	
	public void setTotBlock() {
		totBlock = (int) Math.ceil(totPage / BLOCK_SCALE); // 페이지 블록 갯수 계산 : 5개씩
	}
	
	public void setPageRange() {
		pageBegin = (curPage-1)*PAGE_SCALE + 1;
		pageEnd = pageBegin + PAGE_SCALE - 1;
	}
	
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getTotBlock() {
		return totBlock;
	}
	public void setTotBlock(int totBlock) {
		this.totBlock = totBlock;
	}
	public int getCurBlock() {
		return curBlock;
	}
	public void setCurBlock(int curBlock) {
		this.curBlock = curBlock;
	}
	public int getPrevBlock() {
		return prevBlock;
	}
	public void setPrevBlock(int prevBlock) {
		this.prevBlock = prevBlock;
	}
	public int getNextBlock() {
		return nextBlock;
	}
	public void setNextBlock(int nextBlock) {
		this.nextBlock = nextBlock;
	}
	public int getPageBegin() {
		return pageBegin;
	}
	public void setPageBegin(int pageBegin) {
		this.pageBegin = pageBegin;
	}
	public int getPageEnd() {
		return pageEnd;
	}
	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}
	public int getBlockBegin() {
		return blockBegin;
	}
	public void setBlockBegin(int blockBegin) {
		this.blockBegin = blockBegin;
	}
	public int getBlockEnd() {
		return blockEnd;
	}
	public void setBlockEnd(int blockEnd) {
		this.blockEnd = blockEnd;
	}
	public static int getPageScale() {
		return PAGE_SCALE;
	}
	public static int getBlockScale() {
		return BLOCK_SCALE;
	}
	public int getTotPage() {
		return totPage;
	}
	
}
