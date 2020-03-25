package cn.ekgc.itrip.base.pojo.vo;

import java.util.List;
/**
 * <b>前端页面分页信息对象</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
public class Page<T> {
	private Integer curPage;        //当前页码数
	private Integer total;          //查询总数
	private Integer pageSize;       //每页显示数量
	private Integer pageCount;      //总页数
	private Integer beginPos;       //数据每页其实位置
	private List<T> rows;           //结果集合

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getBeginPos() {
		return beginPos;
	}

	public void setBeginPos(Integer beginPos) {
		this.beginPos = beginPos;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
