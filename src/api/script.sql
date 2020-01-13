
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

    create table comments (
       id bigint not null auto_increment,
        created_on date,
        text varchar(255),
        answer_id bigint,
        user_id bigint,
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

    alter table comments 
       add constraint FKoiwlwqmu9qm0tjnafxqr20rd8 
       foreign key (answer_id) 
       references answers (id)

    alter table comments 
       add constraint FK8omq0tc18jd43bu5tjh6jvraq 
       foreign key (user_id) 
       references users (id)

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
