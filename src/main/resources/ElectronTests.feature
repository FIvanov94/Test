@ElectronTests
Feature: Electron Tests

  @ElectronTest1
  Scenario: Verify volume slider is displayed when hovering on speaker icon
    Given I am in the "Library" section
    When I click the Add Music button
    And I add files successfully
    And I adjust the volume
    Then the volume level should be changed

  @ElectronTest2
  Scenario: Verify Add Music button leads to Settings section
    Given I am in the "Library" section
    When I click the Add Music button
    Then the "Settings" section should be opened


  @ElectronTest3
  Scenario: Verify music can be added
    Given I am in the "Library" section
    When I click the Add Music button
    And I add files
    Then I should see the file

  @ElectronTest4
  Scenario Outline: Verify clicking on <sectionName> footer button leads to <sectionName> section
    Given I am in the "Library" section
    When I click the "<sectionName>" footer button
    Then the "<sectionName>" section should be opened

    Examples:
      | sectionName |
      | Library     |
      | Settings    |
      | Playlists   |

  @ElectronTest5
  Scenario: Verify music can be played
    Given I am in the "Library" section
    When I click the Add Music button
    And I add files successfully
    And I select the first track
    And I press the "Play" button
    Then the track should start playing

  @ElectronTest6
  Scenario: Theme options
    Given I am in the "Library" section
    When I click the "Settings" footer button
    And I click the "Interface" subsection button in settings
    Then I should see the following options after expanding the dropdown menu:
      | options          |
      | System (default) |
      | Light            |
      | Dark             |
      | Dark (legacy)    |

  @ElectronTest7
  Scenario: Create a playlist
    Given I am in the "Library" section
    When I click the "Playlists" footer button
    And I try to create a playlist
    Then the playlist should be created successfully

  @ElectronTest8
  Scenario Outline: Search for artist <artist>
    Given I am in the "Library" section
    When I click the Add Music button
    And I add files successfully
    And I search for artist "<artist>"
    Then only the song by "<artist>" artist should be displayed in the track list

    Examples:
      | artist         |
      | Nightwish      |
      | Russell Allen  |
      | Unknown artist |

  @ElectronTest9
  Scenario: Verify that pressing Next track button skips to the next track in the list
    Given I am in the "Library" section
    When I click the Add Music button
    And I add files successfully
    And I select the first track
    And I press the "Play" button
    And I press the "Next Track" button
    Then The next song should start playing

  @ElectronTest10
  Scenario: Verify that pressing Previous track button skips to the next track in the list
    Given I am in the "Library" section
    When I click the Add Music button
    And I add files successfully
    And I select the first track
    And I press the "Play" button
    And I press the "Next Track" button
    And I press the "Previous Track" button
    Then The previous song should start playing