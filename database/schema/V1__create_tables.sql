PROFILE
CREATE TABLE profile (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    title VARCHAR(150),
    bio VARCHAR(MAX),
    email VARCHAR(150),
    github_url VARCHAR(500),
    linkedin_url VARCHAR(500),
    resume_url VARCHAR(500)
);
EXPERIENCE
CREATE TABLE experience (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    company_name VARCHAR(200) NOT NULL,
    job_title VARCHAR(150) NOT NULL,
    start_date DATE,
    end_date DATE,
    description VARCHAR(MAX),
    profile_id BIGINT,
    CONSTRAINT fk_experience_profile
        FOREIGN KEY (profile_id)
        REFERENCES profile(id)
);
SKILL
CREATE TABLE skill (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(100),
    profile_id BIGINT,
    CONSTRAINT fk_skill_profile
        FOREIGN KEY (profile_id)
        REFERENCES profile(id)
);
PROJECT
CREATE TABLE project (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    description VARCHAR(MAX),
    technologies VARCHAR(500),
    github_url VARCHAR(500),
    live_url VARCHAR(500),
    image_url VARCHAR(500),
    featured BIT DEFAULT 0,
    created_at DATETIME2 DEFAULT GETDATE()
);
ARTWORK
CREATE TABLE artwork (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    description VARCHAR(MAX),
    category VARCHAR(100),
    image_url VARCHAR(500),
    featured BIT DEFAULT 0,
    created_at DATETIME2 DEFAULT GETDATE()
);
CONTACT_MESSAGE
CREATE TABLE contact_message (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    subject VARCHAR(200),
    message VARCHAR(MAX) NOT NULL,
    created_at DATETIME2 NOT NULL
);