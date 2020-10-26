import { expect as expectCDK, matchTemplate, MatchStyle } from '@aws-cdk/assert';
import * as cdk from '@aws-cdk/core';
import * as FinancialViewer from '../lib/financial-viewer-stack';

test('Empty Stack', () => {
    const app = new cdk.App();
    // WHEN
    const stack = new FinancialViewer.FinancialViewerStack(app, 'MyTestStack');
    // THEN
    expectCDK(stack).to(matchTemplate({
      "Resources": {}
    }, MatchStyle.EXACT))
});
