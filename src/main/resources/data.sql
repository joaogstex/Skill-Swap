CREATE TABLE IF NOT EXISTS tb_roles (
    role_id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS tb_users (
    user_id UUID PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS tb_profiles (
    profile_id BIGINT PRIMARY KEY,  -- Será igual ao user_id
    user_id UUID NOT NULL,
    description TEXT,
    image_url VARCHAR(255),
    location VARCHAR(255),
    contact_info VARCHAR(255),
    social_media_links TEXT,
    availability_status VARCHAR(50),
    experience_level VARCHAR(255),
    education_level VARCHAR(255),
    interests TEXT,
    FOREIGN KEY (user_id) REFERENCES tb_users(user_id)
    --FOREIGN KEY (profile_id) REFERENCES tb_users(user_id)
);

CREATE TABLE IF NOT EXISTS tb_skills (
    skill_id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    category VARCHAR(255),
    level VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    profile_id INT,
    FOREIGN KEY (profile_id) REFERENCES tb_profiles(profile_id)
);

CREATE TABLE IF NOT EXISTS tb_user_roles (
    user_id UUID NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES tb_users(user_id),
    FOREIGN KEY (role_id) REFERENCES tb_roles(role_id)
);

CREATE TABLE IF NOT EXISTS tb_user_skills (
    user_id UUID NOT NULL,
    skill_id INT NOT NULL,
    PRIMARY KEY (user_id, skill_id),
    FOREIGN KEY (user_id) REFERENCES tb_users(user_id),
    FOREIGN KEY (skill_id) REFERENCES tb_skills(skill_id)
);

CREATE TABLE IF NOT EXISTS tb_posts (
    post_id INT PRIMARY KEY,
    content TEXT NOT NULL,
    title VARCHAR(255) NOT NULL,
    user_id UUID NOT NULL,
    profile_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES tb_users(user_id),
    FOREIGN KEY (profile_id) REFERENCES tb_profiles(profile_id)
);

-- Dados iniciais
--INSERT INTO tb_roles (role_id, name) VALUES (1, 'USER');
--INSERT INTO tb_roles (role_id, name) VALUES (2, 'ADMIN');
