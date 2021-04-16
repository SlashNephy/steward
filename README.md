# steward

🎭 A Discord Bot which grants read permissions based on the reaction emojis without the requirement of annoying roles.

[![Kotlin](https://img.shields.io/badge/Kotlin-1.4.30-blue)](https://kotlinlang.org)
[![GitHub release (latest by date)](https://img.shields.io/github/v/release/SlashNephy/steward)](https://github.com/SlashNephy/steward/releases)
[![GitHub Workflow Status](https://img.shields.io/github/workflow/status/SlashNephy/steward/Docker)](https://hub.docker.com/r/slashnephy/steward)
[![Docker Image Size (tag)](https://img.shields.io/docker/image-size/slashnephy/steward/latest)](https://hub.docker.com/r/slashnephy/steward)
[![Docker Pulls](https://img.shields.io/docker/pulls/slashnephy/steward)](https://hub.docker.com/r/slashnephy/steward)
[![license](https://img.shields.io/github/license/SlashNephy/steward)](https://github.com/SlashNephy/steward/blob/master/LICENSE)
[![issues](https://img.shields.io/github/issues/SlashNephy/steward)](https://github.com/SlashNephy/steward/issues)
[![pull requests](https://img.shields.io/github/issues-pr/SlashNephy/steward)](https://github.com/SlashNephy/steward/pulls)

[![screenshot.png](https://raw.githubusercontent.com/SlashNephy/steward/master/docs/screenshot.png)](https://github.com/SlashNephy/steward)

## Requirements

- Java 8 or later

## Get Started

### Docker

There are some image tags.

- `slashnephy/steward:latest`  
  Automatically published every push to `master` branch.
- `slashnephy/steward:dev`  
  Automatically published every push to `dev` branch.
- `slashnephy/steward:<version>`  
  Coresponding to release tags on GitHub.

`docker-compose.yml`

```yaml
version: '3.8'

services:
  steward:
    container_name: steward
    image: slashnephy/steward:latest
    restart: always
    volumes:
      - /mnt:/mnt:ro
    environment:
      # タイムゾーン
      TZ: Asia/Tokyo
      # ログレベル
      LOG_LEVEL: TRACE
      # EPGStation 接続情報
      EPGSTATION_HOST: epgstation
      EPGSTATION_PORT: 8888
      # Twitter 資格情報 (必須)
      TWITTER_CK: xxx
      TWITTER_CS: xxx
      TWITTER_AT: xxx
      TWITTER_ATS: xxx
      # 新規の録画済番組を通知するかどうか
      INCLUDE_RECORDED: 1
      # 新規の録画中を通知するかどうか
      INCLUDE_RECORDING: 1
      # 新規の予約を通知するかどうか
      INCLUDE_RESERVES: 1
      # 録画中を通知する間隔 (分)
      RECORDING_POST_FREQUENCY_MINUTES: 10
      # ツイートに PNG 画像を含めるかどうか
      WITH_PNG: 1
      # ツイートに MP4 動画を含めるかどうか
      WITH_MP4: 0
      # 録画先の親ディレクトリ
      MOUNT_POINT: /mnt
```
