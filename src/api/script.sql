
    create table answers (
       id bigint not null auto_increment,
        created_on date,
        edited_on date,
        rating integer,
        text varchar(255),
        user_id bigint,
        question_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table articles (
       id bigint not null auto_increment,
        creator_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table articles_tags (
       article_id bigint not null,
        tag_id bigint not null,
        primary key (article_id, tag_id)
    ) engine=InnoDB

    create table comments (
       id bigint not null auto_increment,
        created_on date,
        text varchar(255),
        answer_id bigint,
        user_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table companies (
       id bigint not null,
        location_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table domains (
       id bigint not null auto_increment,
        name varchar(255),
        company_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table hibernate_sequence (
       next_val bigint
    ) engine=InnoDB

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    create table jobs (
       id bigint not null,
        created_on date,
        payment bigint,
        company_id bigint,
        location_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table locations (
       id bigint not null,
        city varchar(255),
        country varchar(255),
        primary key (id)
    ) engine=InnoDB

    create table questions (
       id bigint not null auto_increment,
        created_on date,
        text varchar(255),
        title varchar(255),
        view_count integer,
        user_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table questions_tags (
       question_id bigint not null,
        tag_id bigint not null,
        primary key (question_id, tag_id)
    ) engine=InnoDB

    create table refreshtoken (
       id bigint not null auto_increment,
        created_at date,
        token varchar(255) not null,
        user_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table tags (
       id bigint not null auto_increment,
        text varchar(255),
        primary key (id)
    ) engine=InnoDB

    create table users (
       id bigint not null auto_increment,
        email varchar(255) not null,
        image varchar(255),
        password varchar(255) not null,
        username varchar(255) not null,
        primary key (id)
    ) engine=InnoDB

    alter table refreshtoken 
       add constraint UK_or156wbneyk8noo4jstv55ii3 unique (token)

    alter table users 
       add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email)

    alter table users 
       add constraint UK_r43af9ap4edm43mmtq01oddj6 unique (username)

    alter table answers 
       add constraint FK5bp3d5loftq2vjn683ephn75a 
       foreign key (user_id) 
       references users (id)

    alter table answers 
       add constraint FK3erw1a3t0r78st8ty27x6v3g1 
       foreign key (question_id) 
       references questions (id)

    alter table articles 
       add constraint FKbt1g844dr9j0v5ghsea6e56df 
       foreign key (creator_id) 
       references users (id)

    alter table articles_tags 
       add constraint FK413pvvanb8va5yfkuvnmfmq1d 
       foreign key (tag_id) 
       references tags (id)

    alter table articles_tags 
       add constraint FKi526292k497p8m4g8pq3tsl3r 
       foreign key (article_id) 
       references articles (id)

    alter table comments 
       add constraint FKoiwlwqmu9qm0tjnafxqr20rd8 
       foreign key (answer_id) 
       references answers (id)

    alter table comments 
       add constraint FK8omq0tc18jd43bu5tjh6jvraq 
       foreign key (user_id) 
       references users (id)

    alter table companies 
       add constraint FKdr3p21hugqr6112d974o9gfws 
       foreign key (location_id) 
       references locations (id)

    alter table domains 
       add constraint FKfhqhpgfd0l2xu26108fmy0qyi 
       foreign key (company_id) 
       references companies (id)

    alter table jobs 
       add constraint FKrtmqcrktb6s7xq8djbs2a2war 
       foreign key (company_id) 
       references companies (id)

    alter table jobs 
       add constraint FKs4mry8ypepr30ypkxxo4w40n5 
       foreign key (location_id) 
       references locations (id)

    alter table questions 
       add constraint FKjoo8hp6d3gfwctr68dl2iaemj 
       foreign key (user_id) 
       references users (id)

    alter table questions_tags 
       add constraint FK7yq8xf1pqv8katljm8v8j8w3c 
       foreign key (tag_id) 
       references tags (id)

    alter table questions_tags 
       add constraint FK4u5xv906wfevngoe973bec6u0 
       foreign key (question_id) 
       references questions (id)

    alter table refreshtoken 
       add constraint FKa652xrdji49m4isx38pp4p80p 
       foreign key (user_id) 
       references users (id)

    create table answers (
       id bigint not null auto_increment,
        created_on date,
        edited_on date,
        rating integer,
        text varchar(255),
        user_id bigint,
        question_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table articles (
       id bigint not null auto_increment,
        creator_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table articles_tags (
       article_id bigint not null,
        tag_id bigint not null,
        primary key (article_id, tag_id)
    ) engine=InnoDB

    create table comments (
       id bigint not null auto_increment,
        created_on date,
        text varchar(255),
        answer_id bigint,
        user_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table companies (
       id bigint not null,
        location_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table domains (
       id bigint not null auto_increment,
        name varchar(255),
        company_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table hibernate_sequence (
       next_val bigint
    ) engine=InnoDB

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    create table jobs (
       id bigint not null,
        created_on date,
        payment bigint,
        company_id bigint,
        location_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table locations (
       id bigint not null,
        city varchar(255),
        country varchar(255),
        primary key (id)
    ) engine=InnoDB

    create table questions (
       id bigint not null auto_increment,
        created_on date,
        text varchar(255),
        title varchar(255),
        view_count integer,
        user_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table questions_tags (
       question_id bigint not null,
        tag_id bigint not null,
        primary key (question_id, tag_id)
    ) engine=InnoDB

    create table refreshtoken (
       id bigint not null auto_increment,
        created_at date,
        token varchar(255) not null,
        user_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table tags (
       id bigint not null auto_increment,
        text varchar(255),
        primary key (id)
    ) engine=InnoDB

    create table users (
       id bigint not null auto_increment,
        email varchar(255) not null,
        image varchar(255),
        password varchar(255) not null,
        username varchar(255) not null,
        primary key (id)
    ) engine=InnoDB

    alter table refreshtoken 
       add constraint UK_or156wbneyk8noo4jstv55ii3 unique (token)

    alter table users 
       add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email)

    alter table users 
       add constraint UK_r43af9ap4edm43mmtq01oddj6 unique (username)

    alter table answers 
       add constraint FK5bp3d5loftq2vjn683ephn75a 
       foreign key (user_id) 
       references users (id)

    alter table answers 
       add constraint FK3erw1a3t0r78st8ty27x6v3g1 
       foreign key (question_id) 
       references questions (id)

    alter table articles 
       add constraint FKbt1g844dr9j0v5ghsea6e56df 
       foreign key (creator_id) 
       references users (id)

    alter table articles_tags 
       add constraint FK413pvvanb8va5yfkuvnmfmq1d 
       foreign key (tag_id) 
       references tags (id)

    alter table articles_tags 
       add constraint FKi526292k497p8m4g8pq3tsl3r 
       foreign key (article_id) 
       references articles (id)

    alter table comments 
       add constraint FKoiwlwqmu9qm0tjnafxqr20rd8 
       foreign key (answer_id) 
       references answers (id)

    alter table comments 
       add constraint FK8omq0tc18jd43bu5tjh6jvraq 
       foreign key (user_id) 
       references users (id)

    alter table companies 
       add constraint FKdr3p21hugqr6112d974o9gfws 
       foreign key (location_id) 
       references locations (id)

    alter table domains 
       add constraint FKfhqhpgfd0l2xu26108fmy0qyi 
       foreign key (company_id) 
       references companies (id)

    alter table jobs 
       add constraint FKrtmqcrktb6s7xq8djbs2a2war 
       foreign key (company_id) 
       references companies (id)

    alter table jobs 
       add constraint FKs4mry8ypepr30ypkxxo4w40n5 
       foreign key (location_id) 
       references locations (id)

    alter table questions 
       add constraint FKjoo8hp6d3gfwctr68dl2iaemj 
       foreign key (user_id) 
       references users (id)

    alter table questions_tags 
       add constraint FK7yq8xf1pqv8katljm8v8j8w3c 
       foreign key (tag_id) 
       references tags (id)

    alter table questions_tags 
       add constraint FK4u5xv906wfevngoe973bec6u0 
       foreign key (question_id) 
       references questions (id)

    alter table refreshtoken 
       add constraint FKa652xrdji49m4isx38pp4p80p 
       foreign key (user_id) 
       references users (id)

    create table answers (
       id bigint not null auto_increment,
        created_on date,
        edited_on date,
        rating integer,
        text varchar(255),
        user_id bigint,
        question_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table articles (
       id bigint not null auto_increment,
        creator_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table articles_tags (
       article_id bigint not null,
        tag_id bigint not null,
        primary key (article_id, tag_id)
    ) engine=InnoDB

    create table comments (
       id bigint not null auto_increment,
        created_on date,
        text varchar(255),
        answer_id bigint,
        user_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table companies (
       id bigint not null,
        location_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table domains (
       id bigint not null auto_increment,
        name varchar(255),
        company_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table hibernate_sequence (
       next_val bigint
    ) engine=InnoDB

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    create table jobs (
       id bigint not null,
        created_on date,
        payment bigint,
        company_id bigint,
        location_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table locations (
       id bigint not null,
        city varchar(255),
        country varchar(255),
        primary key (id)
    ) engine=InnoDB

    create table questions (
       id bigint not null auto_increment,
        created_on date,
        text varchar(255),
        title varchar(255),
        view_count integer,
        user_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table questions_tags (
       question_id bigint not null,
        tag_id bigint not null,
        primary key (question_id, tag_id)
    ) engine=InnoDB

    create table refreshtoken (
       id bigint not null auto_increment,
        created_at date,
        token varchar(255) not null,
        user_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table tags (
       id bigint not null auto_increment,
        text varchar(255),
        primary key (id)
    ) engine=InnoDB

    create table users (
       id bigint not null auto_increment,
        email varchar(255) not null,
        image varchar(255),
        password varchar(255) not null,
        username varchar(255) not null,
        primary key (id)
    ) engine=InnoDB

    alter table refreshtoken 
       add constraint UK_or156wbneyk8noo4jstv55ii3 unique (token)

    alter table users 
       add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email)

    alter table users 
       add constraint UK_r43af9ap4edm43mmtq01oddj6 unique (username)

    alter table answers 
       add constraint FK5bp3d5loftq2vjn683ephn75a 
       foreign key (user_id) 
       references users (id)

    alter table answers 
       add constraint FK3erw1a3t0r78st8ty27x6v3g1 
       foreign key (question_id) 
       references questions (id)

    alter table articles 
       add constraint FKbt1g844dr9j0v5ghsea6e56df 
       foreign key (creator_id) 
       references users (id)

    alter table articles_tags 
       add constraint FK413pvvanb8va5yfkuvnmfmq1d 
       foreign key (tag_id) 
       references tags (id)

    alter table articles_tags 
       add constraint FKi526292k497p8m4g8pq3tsl3r 
       foreign key (article_id) 
       references articles (id)

    alter table comments 
       add constraint FKoiwlwqmu9qm0tjnafxqr20rd8 
       foreign key (answer_id) 
       references answers (id)

    alter table comments 
       add constraint FK8omq0tc18jd43bu5tjh6jvraq 
       foreign key (user_id) 
       references users (id)

    alter table companies 
       add constraint FKdr3p21hugqr6112d974o9gfws 
       foreign key (location_id) 
       references locations (id)

    alter table domains 
       add constraint FKfhqhpgfd0l2xu26108fmy0qyi 
       foreign key (company_id) 
       references companies (id)

    alter table jobs 
       add constraint FKrtmqcrktb6s7xq8djbs2a2war 
       foreign key (company_id) 
       references companies (id)

    alter table jobs 
       add constraint FKs4mry8ypepr30ypkxxo4w40n5 
       foreign key (location_id) 
       references locations (id)

    alter table questions 
       add constraint FKjoo8hp6d3gfwctr68dl2iaemj 
       foreign key (user_id) 
       references users (id)

    alter table questions_tags 
       add constraint FK7yq8xf1pqv8katljm8v8j8w3c 
       foreign key (tag_id) 
       references tags (id)

    alter table questions_tags 
       add constraint FK4u5xv906wfevngoe973bec6u0 
       foreign key (question_id) 
       references questions (id)

    alter table refreshtoken 
       add constraint FKa652xrdji49m4isx38pp4p80p 
       foreign key (user_id) 
       references users (id)

    create table answers (
       id bigint not null auto_increment,
        created_on date,
        edited_on date,
        rating integer,
        text varchar(255),
        user_id bigint,
        question_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table articles (
       id bigint not null auto_increment,
        creator_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table articles_tags (
       article_id bigint not null,
        tag_id bigint not null,
        primary key (article_id, tag_id)
    ) engine=InnoDB

    create table comments (
       id bigint not null auto_increment,
        created_on date,
        text varchar(255),
        answer_id bigint,
        user_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table companies (
       id bigint not null,
        location_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table domains (
       id bigint not null auto_increment,
        name varchar(255),
        company_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table hibernate_sequence (
       next_val bigint
    ) engine=InnoDB

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    create table jobs (
       id bigint not null,
        created_on date,
        payment bigint,
        company_id bigint,
        location_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table locations (
       id bigint not null,
        city varchar(255),
        country varchar(255),
        primary key (id)
    ) engine=InnoDB

    create table questions (
       id bigint not null auto_increment,
        created_on date,
        text varchar(255),
        title varchar(255),
        view_count integer,
        user_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table questions_tags (
       question_id bigint not null,
        tag_id bigint not null,
        primary key (question_id, tag_id)
    ) engine=InnoDB

    create table refreshtoken (
       id bigint not null auto_increment,
        created_at date,
        token varchar(255) not null,
        user_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table tags (
       id bigint not null auto_increment,
        text varchar(255),
        primary key (id)
    ) engine=InnoDB

    create table users (
       id bigint not null auto_increment,
        email varchar(255) not null,
        image varchar(255),
        password varchar(255) not null,
        username varchar(255) not null,
        primary key (id)
    ) engine=InnoDB

    alter table refreshtoken 
       add constraint UK_or156wbneyk8noo4jstv55ii3 unique (token)

    alter table users 
       add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email)

    alter table users 
       add constraint UK_r43af9ap4edm43mmtq01oddj6 unique (username)

    alter table answers 
       add constraint FK5bp3d5loftq2vjn683ephn75a 
       foreign key (user_id) 
       references users (id)

    alter table answers 
       add constraint FK3erw1a3t0r78st8ty27x6v3g1 
       foreign key (question_id) 
       references questions (id)

    alter table articles 
       add constraint FKbt1g844dr9j0v5ghsea6e56df 
       foreign key (creator_id) 
       references users (id)

    alter table articles_tags 
       add constraint FK413pvvanb8va5yfkuvnmfmq1d 
       foreign key (tag_id) 
       references tags (id)

    alter table articles_tags 
       add constraint FKi526292k497p8m4g8pq3tsl3r 
       foreign key (article_id) 
       references articles (id)

    alter table comments 
       add constraint FKoiwlwqmu9qm0tjnafxqr20rd8 
       foreign key (answer_id) 
       references answers (id)

    alter table comments 
       add constraint FK8omq0tc18jd43bu5tjh6jvraq 
       foreign key (user_id) 
       references users (id)

    alter table companies 
       add constraint FKdr3p21hugqr6112d974o9gfws 
       foreign key (location_id) 
       references locations (id)

    alter table domains 
       add constraint FKfhqhpgfd0l2xu26108fmy0qyi 
       foreign key (company_id) 
       references companies (id)

    alter table jobs 
       add constraint FKrtmqcrktb6s7xq8djbs2a2war 
       foreign key (company_id) 
       references companies (id)

    alter table jobs 
       add constraint FKs4mry8ypepr30ypkxxo4w40n5 
       foreign key (location_id) 
       references locations (id)

    alter table questions 
       add constraint FKjoo8hp6d3gfwctr68dl2iaemj 
       foreign key (user_id) 
       references users (id)

    alter table questions_tags 
       add constraint FK7yq8xf1pqv8katljm8v8j8w3c 
       foreign key (tag_id) 
       references tags (id)

    alter table questions_tags 
       add constraint FK4u5xv906wfevngoe973bec6u0 
       foreign key (question_id) 
       references questions (id)

    alter table refreshtoken 
       add constraint FKa652xrdji49m4isx38pp4p80p 
       foreign key (user_id) 
       references users (id)

    create table answers (
       id bigint not null auto_increment,
        created_on date,
        edited_on date,
        rating integer,
        text varchar(255),
        user_id bigint,
        question_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table articles (
       id bigint not null auto_increment,
        creator_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table articles_tags (
       article_id bigint not null,
        tag_id bigint not null,
        primary key (article_id, tag_id)
    ) engine=InnoDB

    create table comments (
       id bigint not null auto_increment,
        created_on date,
        text varchar(255),
        answer_id bigint,
        user_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table companies (
       id bigint not null,
        location_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table domains (
       id bigint not null auto_increment,
        name varchar(255),
        company_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table hibernate_sequence (
       next_val bigint
    ) engine=InnoDB

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    create table jobs (
       id bigint not null,
        created_on date,
        payment bigint,
        company_id bigint,
        location_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table locations (
       id bigint not null,
        city varchar(255),
        country varchar(255),
        primary key (id)
    ) engine=InnoDB

    create table questions (
       id bigint not null auto_increment,
        created_on date,
        text varchar(255),
        title varchar(255),
        view_count integer,
        user_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table questions_tags (
       question_id bigint not null,
        tag_id bigint not null,
        primary key (question_id, tag_id)
    ) engine=InnoDB

    create table refreshtoken (
       id bigint not null auto_increment,
        created_at date,
        token varchar(255) not null,
        user_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table tags (
       id bigint not null auto_increment,
        text varchar(255),
        primary key (id)
    ) engine=InnoDB

    create table users (
       id bigint not null auto_increment,
        email varchar(255) not null,
        image varchar(255),
        password varchar(255) not null,
        username varchar(255) not null,
        primary key (id)
    ) engine=InnoDB

    alter table refreshtoken 
       add constraint UK_or156wbneyk8noo4jstv55ii3 unique (token)

    alter table users 
       add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email)

    alter table users 
       add constraint UK_r43af9ap4edm43mmtq01oddj6 unique (username)

    alter table answers 
       add constraint FK5bp3d5loftq2vjn683ephn75a 
       foreign key (user_id) 
       references users (id)

    alter table answers 
       add constraint FK3erw1a3t0r78st8ty27x6v3g1 
       foreign key (question_id) 
       references questions (id)

    alter table articles 
       add constraint FKbt1g844dr9j0v5ghsea6e56df 
       foreign key (creator_id) 
       references users (id)

    alter table articles_tags 
       add constraint FK413pvvanb8va5yfkuvnmfmq1d 
       foreign key (tag_id) 
       references tags (id)

    alter table articles_tags 
       add constraint FKi526292k497p8m4g8pq3tsl3r 
       foreign key (article_id) 
       references articles (id)

    alter table comments 
       add constraint FKoiwlwqmu9qm0tjnafxqr20rd8 
       foreign key (answer_id) 
       references answers (id)

    alter table comments 
       add constraint FK8omq0tc18jd43bu5tjh6jvraq 
       foreign key (user_id) 
       references users (id)

    alter table companies 
       add constraint FKdr3p21hugqr6112d974o9gfws 
       foreign key (location_id) 
       references locations (id)

    alter table domains 
       add constraint FKfhqhpgfd0l2xu26108fmy0qyi 
       foreign key (company_id) 
       references companies (id)

    alter table jobs 
       add constraint FKrtmqcrktb6s7xq8djbs2a2war 
       foreign key (company_id) 
       references companies (id)

    alter table jobs 
       add constraint FKs4mry8ypepr30ypkxxo4w40n5 
       foreign key (location_id) 
       references locations (id)

    alter table questions 
       add constraint FKjoo8hp6d3gfwctr68dl2iaemj 
       foreign key (user_id) 
       references users (id)

    alter table questions_tags 
       add constraint FK7yq8xf1pqv8katljm8v8j8w3c 
       foreign key (tag_id) 
       references tags (id)

    alter table questions_tags 
       add constraint FK4u5xv906wfevngoe973bec6u0 
       foreign key (question_id) 
       references questions (id)

    alter table refreshtoken 
       add constraint FKa652xrdji49m4isx38pp4p80p 
       foreign key (user_id) 
       references users (id)
