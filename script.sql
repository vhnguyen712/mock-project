USE [master]
GO
/****** Object:  Database [lmsmock]    Script Date: 7/15/2021 2:40:45 PM ******/
CREATE DATABASE [lmsmock]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'lmsmock', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\lmsmock.mdf' , SIZE = 3264KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'lmsmock_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\lmsmock_log.ldf' , SIZE = 832KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [lmsmock] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [lmsmock].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [lmsmock] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [lmsmock] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [lmsmock] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [lmsmock] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [lmsmock] SET ARITHABORT OFF 
GO
ALTER DATABASE [lmsmock] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [lmsmock] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [lmsmock] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [lmsmock] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [lmsmock] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [lmsmock] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [lmsmock] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [lmsmock] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [lmsmock] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [lmsmock] SET  ENABLE_BROKER 
GO
ALTER DATABASE [lmsmock] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [lmsmock] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [lmsmock] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [lmsmock] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [lmsmock] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [lmsmock] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [lmsmock] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [lmsmock] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [lmsmock] SET  MULTI_USER 
GO
ALTER DATABASE [lmsmock] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [lmsmock] SET DB_CHAINING OFF 
GO
ALTER DATABASE [lmsmock] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [lmsmock] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [lmsmock] SET DELAYED_DURABILITY = DISABLED 
GO
USE [lmsmock]
GO
/****** Object:  Table [dbo].[answers]    Script Date: 7/15/2021 2:40:45 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[answers](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[exam_id] [int] NOT NULL,
	[question_id] [int] NOT NULL,
	[choice] [nvarchar](50) NOT NULL,
	[correct] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[chapter]    Script Date: 7/15/2021 2:40:45 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[chapter](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[course_id] [int] NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[description] [nvarchar](500) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[course]    Script Date: 7/15/2021 2:40:45 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[course](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[description] [nvarchar](500) NULL,
	[status] [bit] NOT NULL DEFAULT ((1)),
	[manager_id] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[course_member]    Script Date: 7/15/2021 2:40:45 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[course_member](
	[course_id] [int] NOT NULL,
	[user_id] [int] NOT NULL,
	[status] [bit] NOT NULL DEFAULT ((1)),
PRIMARY KEY CLUSTERED 
(
	[course_id] ASC,
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[exam]    Script Date: 7/15/2021 2:40:45 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[exam](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[duration] [int] NULL,
	[available] [datetime] NOT NULL,
	[due] [datetime] NOT NULL,
	[resourse_id] [int] NOT NULL,
	[create_date] [date] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[grade]    Script Date: 7/15/2021 2:40:45 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[grade](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[exam_id] [int] NOT NULL,
	[user_id] [int] NOT NULL,
	[grade] [float] NOT NULL,
	[correct_answers_count] [int] NOT NULL,
	[questions_count] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[manager]    Script Date: 7/15/2021 2:40:45 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[manager](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[email] [varchar](50) NULL,
	[password] [varchar](64) NULL,
	[name] [varchar](50) NULL,
	[status] [bit] NULL,
	[create_date] [datetime2](7) NULL,
 CONSTRAINT [PK_Manager] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[manager_role]    Script Date: 7/15/2021 2:40:45 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[manager_role](
	[user_id] [int] NOT NULL,
	[role_id] [int] NOT NULL,
 CONSTRAINT [PK_user_role] PRIMARY KEY CLUSTERED 
(
	[user_id] ASC,
	[role_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[question]    Script Date: 7/15/2021 2:40:45 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[question](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[exam_id] [int] NOT NULL,
	[question] [nvarchar](50) NOT NULL,
	[status] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[resource]    Script Date: 7/15/2021 2:40:45 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[resource](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[chapter_id] [int] NOT NULL,
	[url] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[role]    Script Date: 7/15/2021 2:40:45 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[role](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK__role__3213E83F990749DE] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[student_answers]    Script Date: 7/15/2021 2:40:45 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[student_answers](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[exam_id] [int] NOT NULL,
	[user_id] [int] NOT NULL,
	[question_id] [int] NOT NULL,
	[answer_id] [int] NOT NULL,
	[correct] [bit] NOT NULL,
	[completed_time] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[student_exam]    Script Date: 7/15/2021 2:40:45 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[student_exam](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[exam_id] [int] NOT NULL,
	[user_id] [int] NOT NULL,
	[attempts] [int] NOT NULL,
	[status] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[user]    Script Date: 7/15/2021 2:40:45 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[user](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[email] [nvarchar](50) NOT NULL,
	[pass] [nvarchar](64) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[phone] [nvarchar](50) NOT NULL,
	[status] [bit] NOT NULL CONSTRAINT [DF__user__status__145C0A3F]  DEFAULT ((1)),
	[authentication_type] [varchar](10) NULL,
	[create_time] [datetime2](7) NULL,
	[verification_code] [varchar](64) NULL,
	[account_non_locked] [tinyint] NULL CONSTRAINT [DF_user_account_non_locked]  DEFAULT ((1)),
	[failed_attempt] [tinyint] NULL,
	[lock_time] [datetime2](7) NULL,
 CONSTRAINT [PK__user__3213E83F5EBBD5FF] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[user_role]    Script Date: 7/15/2021 2:40:45 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user_role](
	[user_id] [int] NOT NULL,
	[role_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[user_id] ASC,
	[role_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET IDENTITY_INSERT [dbo].[course] ON 

INSERT [dbo].[course] ([id], [name], [description], [status], [manager_id]) VALUES (1, N'OOP ', N'Java OOP', 1, NULL)
INSERT [dbo].[course] ([id], [name], [description], [status], [manager_id]) VALUES (2, N'PRJ123', N'Java PPO', 1, NULL)
INSERT [dbo].[course] ([id], [name], [description], [status], [manager_id]) VALUES (5, N'PPO', N'haha', 1, NULL)
INSERT [dbo].[course] ([id], [name], [description], [status], [manager_id]) VALUES (6, N'LALA', N'123', 1, NULL)
INSERT [dbo].[course] ([id], [name], [description], [status], [manager_id]) VALUES (7, N'Le Quoc Dat', N'haha', 0, NULL)
INSERT [dbo].[course] ([id], [name], [description], [status], [manager_id]) VALUES (8, N'PIPI', N'haha', 1, NULL)
SET IDENTITY_INSERT [dbo].[course] OFF
INSERT [dbo].[course_member] ([course_id], [user_id], [status]) VALUES (1, 30, 1)
INSERT [dbo].[course_member] ([course_id], [user_id], [status]) VALUES (2, 30, 1)
SET IDENTITY_INSERT [dbo].[manager] ON 

INSERT [dbo].[manager] ([id], [email], [password], [name], [status], [create_date]) VALUES (2, N'admin', N'$2a$10$ZkymXji0VIRQbsVyNZD0UOuyXwq4.wq37rNR/H.A2mHB/bMVUn/H6', N'ADMIN', 1, CAST(N'2021-07-14 10:32:00.9900000' AS DateTime2))
INSERT [dbo].[manager] ([id], [email], [password], [name], [status], [create_date]) VALUES (3, N'teacher', N'$2a$10$ZkymXji0VIRQbsVyNZD0UOuyXwq4.wq37rNR/H.A2mHB/bMVUn/H6', N'TEACHER', 1, CAST(N'2021-07-14 10:33:42.2170000' AS DateTime2))
SET IDENTITY_INSERT [dbo].[manager] OFF
INSERT [dbo].[manager_role] ([user_id], [role_id]) VALUES (2, 1)
INSERT [dbo].[manager_role] ([user_id], [role_id]) VALUES (3, 3)
SET IDENTITY_INSERT [dbo].[role] ON 

INSERT [dbo].[role] ([id], [name]) VALUES (1, N'Admin')
INSERT [dbo].[role] ([id], [name]) VALUES (3, N'Teacher')
SET IDENTITY_INSERT [dbo].[role] OFF
SET IDENTITY_INSERT [dbo].[user] ON 

INSERT [dbo].[user] ([id], [email], [pass], [name], [phone], [status], [authentication_type], [create_time], [verification_code], [account_non_locked], [failed_attempt], [lock_time]) VALUES (30, N'dat', N'$2a$10$ZkymXji0VIRQbsVyNZD0UOuyXwq4.wq37rNR/H.A2mHB/bMVUn/H6', N'DAT', N'123', 1, NULL, NULL, NULL, 1, 0, NULL)
INSERT [dbo].[user] ([id], [email], [pass], [name], [phone], [status], [authentication_type], [create_time], [verification_code], [account_non_locked], [failed_attempt], [lock_time]) VALUES (31, N'datlqse140263@fpt.edu.vn', N'$2a$10$zSnsbsMXcly2nnc.DeaUNOv5ySdFLpMTlArl62SgWGO4oLVX0.oiS', N'Le Quoc Dat', N'0868047639', 1, N'DATABASE', CAST(N'2021-07-14 16:04:21.1920000' AS DateTime2), NULL, 1, 0, NULL)
SET IDENTITY_INSERT [dbo].[user] OFF
SET ANSI_PADDING ON

GO
/****** Object:  Index [UQ__user__AB6E6164EFF0E18B]    Script Date: 7/15/2021 2:40:45 PM ******/
ALTER TABLE [dbo].[user] ADD  CONSTRAINT [UQ__user__AB6E6164EFF0E18B] UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[question] ADD  DEFAULT ((1)) FOR [status]
GO
ALTER TABLE [dbo].[student_exam] ADD  DEFAULT ((1)) FOR [status]
GO
ALTER TABLE [dbo].[answers]  WITH CHECK ADD FOREIGN KEY([exam_id])
REFERENCES [dbo].[exam] ([id])
GO
ALTER TABLE [dbo].[answers]  WITH CHECK ADD FOREIGN KEY([question_id])
REFERENCES [dbo].[question] ([id])
GO
ALTER TABLE [dbo].[chapter]  WITH CHECK ADD FOREIGN KEY([course_id])
REFERENCES [dbo].[course] ([id])
GO
ALTER TABLE [dbo].[course]  WITH CHECK ADD  CONSTRAINT [FK_course_manager] FOREIGN KEY([manager_id])
REFERENCES [dbo].[manager] ([id])
GO
ALTER TABLE [dbo].[course] CHECK CONSTRAINT [FK_course_manager]
GO
ALTER TABLE [dbo].[course_member]  WITH CHECK ADD FOREIGN KEY([course_id])
REFERENCES [dbo].[course] ([id])
GO
ALTER TABLE [dbo].[course_member]  WITH CHECK ADD  CONSTRAINT [FK__course_me__user___398D8EEE] FOREIGN KEY([user_id])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[course_member] CHECK CONSTRAINT [FK__course_me__user___398D8EEE]
GO
ALTER TABLE [dbo].[exam]  WITH CHECK ADD FOREIGN KEY([resourse_id])
REFERENCES [dbo].[resource] ([id])
GO
ALTER TABLE [dbo].[grade]  WITH CHECK ADD FOREIGN KEY([exam_id])
REFERENCES [dbo].[exam] ([id])
GO
ALTER TABLE [dbo].[grade]  WITH CHECK ADD  CONSTRAINT [FK__grade__user_id__300424B4] FOREIGN KEY([user_id])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[grade] CHECK CONSTRAINT [FK__grade__user_id__300424B4]
GO
ALTER TABLE [dbo].[manager_role]  WITH CHECK ADD  CONSTRAINT [FK_user_role_Manager] FOREIGN KEY([user_id])
REFERENCES [dbo].[manager] ([id])
GO
ALTER TABLE [dbo].[manager_role] CHECK CONSTRAINT [FK_user_role_Manager]
GO
ALTER TABLE [dbo].[manager_role]  WITH CHECK ADD  CONSTRAINT [FK_user_role_role] FOREIGN KEY([role_id])
REFERENCES [dbo].[role] ([id])
GO
ALTER TABLE [dbo].[manager_role] CHECK CONSTRAINT [FK_user_role_role]
GO
ALTER TABLE [dbo].[question]  WITH CHECK ADD FOREIGN KEY([exam_id])
REFERENCES [dbo].[exam] ([id])
GO
ALTER TABLE [dbo].[resource]  WITH CHECK ADD FOREIGN KEY([chapter_id])
REFERENCES [dbo].[chapter] ([id])
GO
ALTER TABLE [dbo].[student_answers]  WITH CHECK ADD FOREIGN KEY([answer_id])
REFERENCES [dbo].[answers] ([id])
GO
ALTER TABLE [dbo].[student_answers]  WITH CHECK ADD FOREIGN KEY([exam_id])
REFERENCES [dbo].[exam] ([id])
GO
ALTER TABLE [dbo].[student_answers]  WITH CHECK ADD FOREIGN KEY([question_id])
REFERENCES [dbo].[question] ([id])
GO
ALTER TABLE [dbo].[student_answers]  WITH CHECK ADD  CONSTRAINT [FK__student_a__user___33D4B598] FOREIGN KEY([user_id])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[student_answers] CHECK CONSTRAINT [FK__student_a__user___33D4B598]
GO
ALTER TABLE [dbo].[student_exam]  WITH CHECK ADD FOREIGN KEY([exam_id])
REFERENCES [dbo].[exam] ([id])
GO
ALTER TABLE [dbo].[student_exam]  WITH CHECK ADD  CONSTRAINT [FK__student_e__user___2B3F6F97] FOREIGN KEY([user_id])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[student_exam] CHECK CONSTRAINT [FK__student_e__user___2B3F6F97]
GO
ALTER TABLE [dbo].[user_role]  WITH CHECK ADD  CONSTRAINT [FKa68196081fvovjhkek5m97n3y] FOREIGN KEY([role_id])
REFERENCES [dbo].[role] ([id])
GO
ALTER TABLE [dbo].[user_role] CHECK CONSTRAINT [FKa68196081fvovjhkek5m97n3y]
GO
ALTER TABLE [dbo].[user_role]  WITH CHECK ADD  CONSTRAINT [FKfgsgxvihks805qcq8sq26ab7c] FOREIGN KEY([user_id])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[user_role] CHECK CONSTRAINT [FKfgsgxvihks805qcq8sq26ab7c]
GO
USE [master]
GO
ALTER DATABASE [lmsmock] SET  READ_WRITE 
GO
