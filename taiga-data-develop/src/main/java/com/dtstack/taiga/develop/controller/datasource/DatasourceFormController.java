package com.dtstack.taiga.develop.controller.datasource;


import com.dtstack.taiga.common.exception.RdosDefineException;
import com.dtstack.taiga.common.lang.coc.APITemplate;
import com.dtstack.taiga.common.lang.web.R;
import com.dtstack.taiga.develop.bo.datasource.DsTypeVersionParam;
import com.dtstack.taiga.develop.service.datasource.impl.DsFormFieldService;
import com.dtstack.taiga.develop.utils.Asserts;
import com.dtstack.taiga.develop.vo.datasource.DsFormTemplateVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @description:
 * @author: liuxx
 * @date: 2021/3/9
 */
@Api(tags = {"数据源中心-数据源表单模版化"})
@RestController
@RequestMapping(value = "/node/datasource/dsForm")
public class DatasourceFormController {

    @Autowired
    private DsFormFieldService dsFormFieldService;

    @ApiOperation("根据数据库类型和版本查找表单模版")
    @PostMapping("/findFormByTypeVersion")
    public R<DsFormTemplateVo> findTemplateByTypeVersion(@RequestBody DsTypeVersionParam param) {
        return new APITemplate<DsFormTemplateVo>() {
            @Override
            protected void checkParams() throws IllegalArgumentException {
                Asserts.hasText(param.getDataType(), "数据源类型不能为空!");
            }
            @Override
            protected DsFormTemplateVo process() throws RdosDefineException {
                return dsFormFieldService.findTemplateByTypeVersion(param);
            }
        }.execute();
    }


}