@tag
Feature: Feature description

  Background: Description for pre-step before each scenario
    Then set "value" to 'name' field

  @TestCaseKey={jira-ticket}
  Scenario: Scenario description
    Given send 100 packets of "netflow9" traffic to 9999 port
    Then set "value" to 'name' field
