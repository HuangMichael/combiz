package combiz.business.tool;

import combiz.domain.tool.Tool;
import combiz.system.IBOBaseSrv;

import java.util.List;

public interface ToolSrv extends IBOBaseSrv
{
	public void geninvuse(List list) throws Exception;
	public void genreturn(List list) throws Exception;
}
