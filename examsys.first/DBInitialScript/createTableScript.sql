-- Create syntax for '(null)'

-- Create syntax for TABLE 'exam_answer_tbl'
CREATE TABLE `exam_answer_tbl` (
  `answer_id` bigint(10) NOT NULL AUTO_INCREMENT,
  `question_id` bigint(10) DEFAULT NULL,
  `answer_content` varchar(200) NOT NULL,
  `selected` tinyint(2) DEFAULT NULL COMMENT '0:未选\n1:已选',
  `correct_flag` tinyint(2) DEFAULT NULL COMMENT '0:不正确\n1:正确',
  PRIMARY KEY (`answer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考试成绩选择答案';

-- Create syntax for TABLE 'exam_category_compose_tbl'
CREATE TABLE `exam_category_compose_tbl` (
  `code` varchar(10) NOT NULL,
  `type` int(11) NOT NULL,
  `count` int(11) DEFAULT NULL,
  PRIMARY KEY (`code`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考试类别组成表';

-- Create syntax for TABLE 'exam_category_tbl'
CREATE TABLE `exam_category_tbl` (
  `code` varchar(10) NOT NULL,
  `name` varchar(100) NOT NULL DEFAULT '未编辑类别名',
  `score` int(11) DEFAULT NULL,
  `time` int(11) NOT NULL DEFAULT '30',
  `pass_score` int(11) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考试类别表';

-- Create syntax for TABLE 'exam_item_tbl'
CREATE TABLE `exam_item_tbl` (
  `item_id` bigint(10) NOT NULL,
  `code` varchar(10) NOT NULL,
  `item_contont` varchar(200) NOT NULL,
  `score` int(11) NOT NULL DEFAULT '2',
  `type` tinyint(2) NOT NULL COMMENT '1:单选\n2:多选\n3:判断\n4:客观',
  `memo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考题内容表';

-- Create syntax for TABLE 'exam_option_tbl'
CREATE TABLE `exam_option_tbl` (
  `option_id` bigint(10) NOT NULL AUTO_INCREMENT,
  `item_id` bigint(10) NOT NULL,
  `option_content` varchar(200) NOT NULL DEFAULT '未完善的备选答案',
  `correct_flag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0:不正确\n1:正确',
  PRIMARY KEY (`option_id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8 COMMENT='考题备选答案表';

-- Create syntax for TABLE 'exam_question_tbl'
CREATE TABLE `exam_question_tbl` (
  `question_id` bigint(10) NOT NULL,
  `testpaper_id` bigint(10) DEFAULT NULL,
  `question_content` varchar(200) NOT NULL,
  `seq` int(11) NOT NULL,
  `question_type` int(2) DEFAULT NULL,
  `correct_flag` tinyint(2) NOT NULL COMMENT '0:不正确\n1:正确',
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考试试题表';

-- Create syntax for TABLE 'exam_testpaper_tbl'
CREATE TABLE `exam_testpaper_tbl` (
  `testpaper_id` bigint(10) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(100) NOT NULL,
  `code` varchar(10) DEFAULT NULL,
  `total_time` int(11) DEFAULT NULL,
  `spend_time` int(11) DEFAULT NULL,
  `total_score` int(11) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `item_count` int(11) DEFAULT NULL,
  `pass_flag` tinyint(2) NOT NULL COMMENT '0:未通过\n1:通过\n',
  `pass_score` int(11) DEFAULT NULL,
  `test_datetime` varchar(45) DEFAULT NULL,
  `user_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`testpaper_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='考卷表';
