#!/usr/bin/env node
import 'source-map-support/register';
import * as cdk from '@aws-cdk/core';
import { FinancialViewerStack } from '../lib/financial-viewer-stack';

const app = new cdk.App();
new FinancialViewerStack(app, 'FinancialViewerStack');
