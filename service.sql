use master
go
if exists(select * from sysdatabases where name='SuperAssociation')
	drop database SuperAssociation
go
create database SuperAssociation
go
use SuperAssociation
go
create table UserMain(
	umid int identity primary key not null,
	name Varchar(20) unique not null,
	password Varchar(20) not null,
	nichen Varchar(20) not null,
	Touxiang Varchar(100),
	sex Bit not null,
	age Int,
	brithday datetime,
	telphone varchar(20),
	myclass varchar(20)
)
create table SheTuan(
	stid int identity primary key not null,
	sname Varchar(20) unique not null,
	snotice Varchar(500),
	sdescribe Varchar(500),
	cjdate datetime not null,
	umid int not null,
	foreign key(umid) references UserMain(umid)
)
create table U2st(
	U2stid int identity primary key not null,
	umid int not null,
	stid int not null,
	foreign key(umid) references UserMain(umid),
	foreign key(stid) references Shetuan(stid)
)
create table Activity(
	aid int identity primary key not null,
	stid int not null,
	adescribe varchar(500) not null,
	atitle varchar(50) not null,
	sdate datetime not null,
	edate datetime not null,
	foreign key(stid) references Shetuan(stid)
)
create table ActivityTopic(
	atid int identity primary key not null,
	attext varchar(500),
	type int not null,
	umidme int not null,
	umidyou int not null,
	ispublic bit not null,
	atdate datetime not null,
	foreign key(umidme) references UserMain(umid),
	foreign key(umidyou) references UserMain(umid)
)
create table ActivityTopicImg(
	imgid int identity primary key not null,
	imgpath varchar(100) not null,
	atid int not null,
	foreign key(atid) references ActivityTopic(atid)
)
create table ShetuanAdmin(
	staid int identity primary key not null,
	stid int not null,
	umid int not null,
	foreign key(stid) references SheTuan(stid),
	foreign key(umid) references UserMain(umid)
)
create table Tongzhi(
	tzid int identity primary key not null,
	tztext varchar(500) not null,
	tztitle	varchar(50) not null,
	umid int not null,
	stid int not null,
	tzdate datetime not null,
	foreign key(stid) references SheTuan(stid),
	foreign key(umid) references UserMain(umid)
)
create table Xiakeliao(
	xklid int identity primary key not null,
	xkltext varchar(500) not null,
	type int not null,
	umid int not null,
	cjdate datetime not null,
	foreign key(umid) references UserMain(umid)
)
create table XiakeliaoImg(
	xklimgid int identity primary key not null,
	imgpath varchar(100) not null,
	xklid int not null,
	foreign key(xklid) references Xiakeliao(xklid)
)
create table XiakeliaoReply(
	xklrid int identity primary key not null,
	xklid int not null,
	umidme int not null,
	umidyou int not null,
	xklrtext varchar(200)not null,
	cjdate datetime not null,
	foreign key(xklid) references Xiakeliao(xklid),
	foreign key(umidme) references UserMain(umid),
	foreign key(umidyou) references UserMain(umid)
)