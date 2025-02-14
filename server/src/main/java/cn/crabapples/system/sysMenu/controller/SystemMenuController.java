package cn.crabapples.system.sysMenu.controller;

import cn.crabapples.common.base.BaseController;
import cn.crabapples.common.base.ResponseDTO;
import cn.crabapples.common.utils.Groups;
import cn.crabapples.system.sysMenu.entity.SysMenu;
import cn.crabapples.system.sysMenu.form.MenusForm;
import cn.crabapples.system.sysMenu.service.SystemMenusService;
import cn.crabapples.system.sysRoleMenu.service.SystemRoleMenusService;
import com.mybatisflex.core.paginate.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TODO 系统菜单相关接口
 *
 * @author Mr.He
 * 2021/4/24 23:57
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */

@RestController
@Tag(name = "系统接口[菜单]")
@Slf4j
@RequestMapping("/api/system/menu")
public class SystemMenuController extends BaseController {

    private final SystemMenusService menusService;
    private final SystemRoleMenusService roleMenusService;

    public SystemMenuController(SystemMenusService menusService, SystemRoleMenusService roleMenusService) {
        this.menusService = menusService;
        this.roleMenusService = roleMenusService;
    }

    /**
     * 获取[当前用户]菜单树
     */
    @GetMapping("/user")
    @Operation(summary = "获取当前用户菜单树", description = "获取当前用户菜单树接口")
    public ResponseDTO<List<SysMenu>> getUserMenus() {
        log.info("收到请求->获取[当前用户]菜单树");
        List<SysMenu> menus = menusService.getUserMenusTree();
        log.debug("返回结果->获取[当前用户]菜单树:[{}]", menus);
        return new ResponseDTO<>(menus);
    }

    /**
     * 获取[所有]菜单列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取所有菜单树", description = "获取所有菜单树接口")
    public ResponseDTO<List<SysMenu>> getMenusList() {
        log.info("收到请求->获取[所有]菜单列表");
        List<SysMenu> list = menusService.getMenusList();
        log.debug("返回结果->获取[所有]菜单列表成功:[{}]", list);
        return new ResponseDTO<>(list);
    }

    /**
     * 获取[所有]菜单列表
     */
    @GetMapping("/page")
    @Operation(summary = "获取所有菜单列表", description = "获取所有菜单列表接口")
    public ResponseDTO<Page<SysMenu>> getMenuPage(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                                  @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                  MenusForm form) {
        log.info("收到请求->获取菜单列表");
        Page<SysMenu> list = menusService.getMenuPage(pageIndex,pageSize,form);
        log.debug("返回结果->获取菜单列表成功:[{}]", list);
        return new ResponseDTO<>(list);
    }


    /**
     * 获取[角色]菜单树
     */
    @GetMapping("/tree/role/{id}")
    @Operation(summary = "获取角色菜单树", description = "获取角色菜单列表树")
    public ResponseDTO<List<SysMenu>> getRoleMenusTree(@PathVariable String id) {
        log.info("收到请求->获取[角色]菜单列表(tree):[{}]", id);
        List<SysMenu> list = roleMenusService.getRoleMenusTree(id);
        log.debug("返回结果->获取[角色]菜单列表(tree)成功:[{}]", list);
        return new ResponseDTO<>(list);
    }

      /**
     * 获取[角色]菜单列表
     */
    @GetMapping("/role/{id}")
    @Operation(summary = "获取角色菜单列表", description = "获取角色菜单列表列表")
    public ResponseDTO<List<SysMenu>> getRoleMenus(@PathVariable String id) {
        log.info("收到请求->获取[角色]菜单列表(list):[{}]", id);
        List<SysMenu> list = roleMenusService.getRoleMenusList(id);
        log.debug("返回结果->获取[角色]菜单列表成功(list):[{}]", list);
        return new ResponseDTO<>(list);
    }

    /**
     * 保存菜单
     */
    @PostMapping("/save")
    public ResponseDTO<Boolean> saveMenus(@RequestBody MenusForm form) {
        super.validator(form, Groups.IsAdd.class, Groups.IsEdit.class);
        log.info("收到请求->保存菜单:[{}]", form);
        boolean status = menusService.saveMenus(form);
        log.debug("返回结果->保存菜单成功:[{}]", status);
        return new ResponseDTO<>(status);
    }

    /**
     * 删除菜单
     */
    @DeleteMapping("/remove/{id}")
    @Operation(summary = "保存菜单", description = "保存菜单")
    public ResponseDTO<Boolean> removeMenus(@PathVariable String id) {
        log.info("收到请求->删除菜单:[{}]", id);
        boolean status = menusService.removeMenus(id);
        log.debug("返回结果->删除菜单成功:[{}]", status);
        return new ResponseDTO<>(status);
    }

}
