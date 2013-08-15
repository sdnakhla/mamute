package br.com.caelum.brutal.migration.all;

import br.com.caelum.brutal.migration.SimpleSchemaMigration;
import br.com.caelum.vraptor4.ioc.ApplicationScoped;

@ApplicationScoped
public class M002CreateTableTagPage extends SimpleSchemaMigration {

	@Override
	public String upQuery() {
		return	"    create table TagPage (\n" + 
				"        id bigint not null auto_increment,\n" + 
				"        about longtext not null,\n" + 
				"        markedAbout longtext not null,\n" + 
				"        tag_id bigint,\n" + 
				"        primary key (id)\n" + 
				"    ) ENGINE=InnoDB";
	}
	
	@Override
	public String downQuery() {
		return "drop table TagPage";
	}

}
