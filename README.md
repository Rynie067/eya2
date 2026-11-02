# Rynie Voice

Windows desktop microphone processor for Discord. The project is built from scratch in this repository.

## Current target

- Windows 10 (1903+) and Windows 11, x64.
- Real-time microphone gain, pitch shifting (deeper voice), bass warmth, compression and a basic noise gate.
- Routes the processed signal to **VB-CABLE Input**; Discord should use **CABLE Output (VB-Audio Virtual Cable)** as its microphone.
- One downloadable Inno Setup installer. The installer includes the official VB-CABLE package so the user does not have to find or download a second installer.

## Important VB-CABLE notice

VB-CABLE is a separate product by VB-Audio Software and remains identifiable as **VB-Audio VB-CABLE** in the setup and application. It is donationware. Users can donate or purchase a license at https://shop.vb-audio.com/en/win-apps/11-vb-cable.html. Official origin and licensing information: https://vb-audio.com/Services/licensing.htm. The VB-CABLE package is obtained from the official download page: https://vb-audio.com/Cable/.

Rynie Voice does not claim ownership of VB-CABLE. Its driver is installed by the official VB-Audio setup program packaged inside the Rynie Voice installer under VB-Audio's published distribution conditions. The user must accept the driver installation/UAC prompt. A Windows restart may be required.

## Build

The GitHub Actions workflow builds a self-contained Windows x64 application, downloads the official VB-CABLE Pack 45 from VB-Audio, and creates one Inno Setup installer.

## Status

This repository started empty. The initial implementation is being added; the first Windows CI build is the compile check. Hardware/Discord end-to-end testing still needs a real Windows PC and microphone.

## Privacy

All audio processing is local. Rynie Voice does not send microphone audio to a server and does not record audio to disk.
