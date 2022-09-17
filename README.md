# The Lake of Kalandra Problem

Inspired by Path of Exile's 3.19 expansion, the Lake of Kalandra problem is an optimization problem revolving around limited shifting of connected vertices to maximize reward.


In a 2D grid exists a series of tiles, either classified as water or land tiles. The land tiles initially spawn in randomized locations in the 2D grid in a way such that all the land tiles are adjacent to at least one other land tile. If a land tile does not have adjacent land tiles, it is considered unreachable and does not qualify for rewards (more on that later).

One land tile is also designated as the entrance tile. There can only be one entrance tile in the lake at one time.

Each land tile yields reward (except for the entrance tile). The worth of a land tile is equal to the shortest amount of adjacent tiles from the entrance to that tile.

The main purpose of this code is to capture a lake and transform it into a workable graph that we can use to try and optimize.

