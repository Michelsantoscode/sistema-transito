create table veiculos (
    id bigint not null auto_increment,
    proprietario_id bigint not null,
    marca varchar(20) not null,
    modelo varchar (20) not null,
    placa varchar(7) not null,
    status varchar (20) not null,
    data_cadastro datetime not null,
    data_apreensao datetime,

    primary key (id)

);

alter table veiculos add constraint fk_veiculo_proprietario
foreign key (proprietario_id) references proprietario(id);
alter table veiculos add constraint uk_veiculo unique(placa);