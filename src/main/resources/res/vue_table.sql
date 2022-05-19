use ssafyweb;

CREATE TABLE IF NOT EXISTS `ssafyweb`.`board` (
  `articleno` INT NOT NULL AUTO_INCREMENT,
  `userid` VARCHAR(16) NULL DEFAULT NULL,
  `subject` VARCHAR(100) NULL DEFAULT NULL,
  `content` VARCHAR(2000) NULL DEFAULT NULL,
  `hit` INT NULL DEFAULT 0,
  `regtime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`articleno`),
  INDEX `board_to_member_fk` (`userid` ASC) VISIBLE,
  CONSTRAINT `board_to_member_fk`
    FOREIGN KEY (`userid`)
    REFERENCES `ssafyweb`.`ssafy_member` (`userid`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


insert into board(userid, subject, content) 
values('ssafy', 'Vue Axios 연동', 'Vue를 이용한 HTTP 통신'), 
      ('ssafy', 'Vue를 배워봅시다', 'Vue와 Spring을 연동하자~'),
      ('admin', '뷰와 스프링부트를 이용한 실전 프로젝트', '프로젝트를 직접만드는 내용.'),
      ('ssafy', '프론트엔드 프레임워크', 'Vue는 프론트엔드의 인기있는 프레임워크 입니다.');
      
commit;