Health Exporter
==============
---



---

# docker

`docker image build -t health-exporter:1.1.0 .
`

`docker run -d -p 1234:1234 health-exporter:1.1.0
`

`docker run -d -e EXPORTER_CONFIG_PATH='/home/config.yml' -p 1234:1234 health-exporter:1.1.0
`
---