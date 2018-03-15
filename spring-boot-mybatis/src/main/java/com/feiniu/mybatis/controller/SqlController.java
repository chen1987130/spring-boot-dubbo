package com.feiniu.mybatis.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.feiniu.framework.paginter.domain.JsonTable;
import com.feiniu.framework.paginter.domain.PageBounds;
import com.feiniu.framework.paginter.domain.PageList;
import com.feiniu.mybatis.mapper.CityMapper;
import com.feiniu.mybatis.mapper.CountryMapper;
import com.feiniu.mybatis.model.City;
import com.feiniu.mybatis.model.Country;
import com.feiniu.mybatis.model.Employee;
import com.feiniu.mybatis.model.PageTable;
import com.feiniu.mybatis.service.CountryService;
import com.feiniu.mybatis.service.EmployeeService;

@RestController
@RequestMapping("/sql")
public class SqlController {

	@Autowired
	private CityMapper cityMapper;

	@Autowired
	private CountryMapper countryMapper;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private CountryService countryService;

	@RequestMapping("/list")
	public List<City> list() {
		return cityMapper.finaAll();
	}

	@RequestMapping("/selectOne")
	public City selectOne(@RequestParam Integer id) {
		return cityMapper.selectOne(id);
	}

	@RequestMapping("/listForPage")
	public JsonTable<Country> listForPage(@RequestParam Integer page, @RequestParam Integer pageSize, @RequestParam(required = false) String countryname) {
		PageBounds bound = new PageBounds(page, pageSize);
		PageList<Country> list;
		if (countryname != null) {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("cityMapper", countryname);
			list = cityMapper.queryForPageByMap(queryMap, bound);
		} else {
			list = cityMapper.queryForPage(bound);
		}
		return new JsonTable<Country>(list, list.getPaginator().getTotalCount());
	}

	@RequestMapping("/countryQuery")
	public JsonTable<Country> countryQuery(@RequestParam Integer page, @RequestParam Integer pageSize) {
		PageBounds bound = new PageBounds(page, pageSize);
		PageList<Country> list = countryMapper.select(bound);
		return new JsonTable<Country>(list, list.getPaginator().getTotalCount());
	}

	@RequestMapping("/insertEmployee")
	public String insertEmployee() {

		Random r = new Random();
		String letter = "赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董梁杜阮蓝闵席季麻强贾路娄危江童颜郭梅盛林刁锺徐邱骆高夏蔡田樊胡凌霍虞万支柯昝管卢莫经房裘缪干解应宗丁宣贲邓郁单杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁荀羊於惠甄麴家封芮羿储靳汲邴糜松井段富巫乌焦巴弓牧隗山谷车侯宓蓬全郗班仰秋仲伊宫宁仇栾暴甘钭历戎祖武符刘景詹束龙叶幸司韶郜黎蓟溥印宿白怀蒲邰从鄂索咸籍赖卓蔺屠蒙池乔阳郁胥能苍双闻莘党翟谭贡劳逄姬申扶堵冉宰郦雍却璩桑桂濮牛寿通边扈燕冀僪浦尚农温别庄晏柴瞿阎充慕连茹习宦艾鱼容向古易慎戈廖庾终暨居衡步都耿满弘匡国文寇广禄阙东欧殳沃利蔚越夔隆师巩厍聂晁勾敖融冷訾辛阚那简饶空曾毋沙乜养鞠须丰巢关蒯相查后荆红游竺权逮盍益桓公";

		for (int i = 0; i < 1000; i++) {
			Employee employee = new Employee();
			String name = "";
			for (int p = 0; p < 3; p++) {
				int index = r.nextInt(letter.length() - 1);
				name = name + letter.substring(index, index + 1);
			}
			employee.setName(name);
			employee.setAge(r.nextInt(120));
			employeeService.insert(employee);
		}

		return "success";
	}

	@RequestMapping("/listEmployee")
	public PageTable listEmployee(@RequestParam Integer page, @RequestParam Integer pageSize) throws Exception {
		return employeeService.queryListOrderByAge(page, pageSize);
	}

	@RequestMapping("/employeeTx")
	public String employeeTx() throws Exception {
		countryService.batchOpt();
		return "success";
	}

	public static void main(String[] args) throws IOException {
		String str = "北京市(朝阳区)(西城区)(海淀区)";
		Pattern p = Pattern.compile(".*?(?=\\()");
		Matcher m = p.matcher(str);
		if (m.find()) {
			System.out.println(m.group());
		}
	}
}
