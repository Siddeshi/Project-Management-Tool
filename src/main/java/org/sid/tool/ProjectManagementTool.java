package org.sid.tool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Standalone spring boot application for project management in a company
 * It provides multiple features like, prodcuct backlog, teams which are working on the projects
 * users in the team, users can add new project new backlog also they can like and comment on them
 *
 * @author siddesh
 * @since 08/Jan/2020
 */
@SpringBootApplication
public class ProjectManagementTool {
    public static void main(String[] args) {
        SpringApplication.run(ProjectManagementTool.class, args);
    }
}
